package com.wxtest.demo.common.hbase;

import com.wxtest.demo.common.hbase.IHbase.HBaseSolrExtraDetailsDao;
import com.wxtest.demo.entity.SolrExtraDetails;
import com.wxtest.demo.utils.Page;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * core6hbase接口实现类 Created by 孟庆艺 on 2017-07-06.
 */
@Repository
public class HBaseSolrExtraDetailsDaoImpl implements HBaseSolrExtraDetailsDao {

    private static final Logger logger = LoggerFactory.getLogger("log.hbase.HBaseSolrExtraDetailsDaoImpl");
    @Autowired
    @Qualifier("phoenixJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    //导数
    @Override
    public boolean findExtraDetails(Long userId, String phone, String sensitivityPhone) {
        //select OTHERPHONE from extralDetails where userid = 1 and
        //phone = '18632156680' and OTHERPHONE='02120309000' limit 1;
        String sql = "select OTHERPHONE from extralDetails where userId = " + userId + " and phone= '" + phone + "'  "
                + "and OTHERPHONE='" + sensitivityPhone + "'";
        List<SolrExtraDetails> solrExtraDetailsList = new ArrayList<>();
        solrExtraDetailsList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SolrExtraDetails.class));
        if (solrExtraDetailsList.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteExtraDetails(Long userId, String phone) {
        logger.info("【Hbase-core6】进入敏感通话详单(core6)deleteExtraDetails方法");
        if (userId == null || phone == null) {
            logger.warn("【Hbase-core6】敏感通话详单(core6)入参存在空值,直接返回,userId={},phone={}", userId, phone);
            return;
        }
        // //如果hbase故障,直接返回 阻绝再向队列中发起请求
        // if (!HBASE_ACTIVE) {
        //   logger.error("[deleteExtraDetails]===hbase故障,直接返回 阻绝请求积压,不再向队列中发起请求");
        //   return;
        // }
        String sql = "delete from extralDetails where userId = " + userId + " and phone= '" + phone + "'";
        logger.warn("【Hbase-core6】进入敏感通话详单(core6),sql={}", sql);
        try {
            long startTime = System.currentTimeMillis();
            jdbcTemplate.execute(sql);
            long endTime = System.currentTimeMillis();
            float excTime = (float) (endTime - startTime) / 1000;
            logger.info("【Hbase-core6】敏感通话详单(core6)执行删除方法成功!!sql:" + sql + "，耗时：" + excTime);
        } catch (Exception e) {
            logger.error("【Hbase-core6】敏感通话详单(core6)执行删除方法失败！！！sql:" + sql + "，失败原因:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void addExtraDetails(Collection<SolrExtraDetails> extraDetailsList) {
        logger.warn("【Hbase-core6】进入敏感通话详单(core6)批量插入方法");
        if (CollectionUtils.isEmpty(extraDetailsList)) {
            logger.error("【Hbase-core6】进入敏感通话详单(core6)入参extraDetailsList为NULL");
            return;
        }
        // 获取集合长度
        final int size = extraDetailsList.size();// 其实应该写实体类的属性数量
        String sql = "upsert into extralDetails VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<SolrExtraDetails> solrExtraDetailsList = new ArrayList<>(extraDetailsList);
        logger.warn("【Hbase-core6】进入敏感通话详单(core6)方法,user_Id={},sql={}", solrExtraDetailsList.get(0).getUserId(), sql);
        // 创建一个 实体类长度的数组
        Object args[] = new Object[17];
        long startTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < size; i++) {
                try {
                    // 设置完入参
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
                    args[0] = uuid;
                    args[1] = solrExtraDetailsList.get(i).getUserId();
                    args[2] = StringFilter(solrExtraDetailsList.get(i).getPhone());
                    args[3] = StringFilter(solrExtraDetailsList.get(i).getOtherPhone());
                    args[4] = StringFilter(solrExtraDetailsList.get(i).getFirstCall());
                    args[5] = StringFilter(solrExtraDetailsList.get(i).getLastCall());
                    args[6] = solrExtraDetailsList.get(i).getInTimes();
                    args[7] = solrExtraDetailsList.get(i).getOutTimes();
                    args[8] = solrExtraDetailsList.get(i).getInDuration();
                    args[9] = solrExtraDetailsList.get(i).getOutDuration();
                    args[10] = solrExtraDetailsList.get(i).getInFee();
                    args[11] = solrExtraDetailsList.get(i).getOutFee();
                    args[12] = solrExtraDetailsList.get(i).getAllTimes();
                    args[13] = StringFilter(solrExtraDetailsList.get(i).getCallLocation());
                    args[14] = StringFilter(solrExtraDetailsList.get(i).getType());
                    args[15] = StringFilter(solrExtraDetailsList.get(i).getPlatform());
                    args[16] = StringFilter(solrExtraDetailsList.get(i).getCommType());
                    jdbcTemplate.update(sql, args);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("【Hbase-core6】敏感通话详单(core6)添加方法执行异常,user_id=" + args[1] + "，sql:" + sql + "，失败原因:" + e);

                }
            }
            long endTime = System.currentTimeMillis();
            float excTime = (float) (endTime - startTime) / 1000;
            logger.warn("【Hbase-core6】敏感通话详单(core6)添加方法执行完毕,user_id={},size={},耗时={}",
                    solrExtraDetailsList.get(0).getUserId(), solrExtraDetailsList.size(), excTime);
        } catch (Exception e) {
            logger.error("【Hbase-core6】敏感通话详单(core6)添加方法循环异常：", e);
            e.printStackTrace();
        }

    }

    @Override
    public Page<SolrExtraDetails> queryExtraDetails(Long userId, String phone, String extraParamter,
            Page<SolrExtraDetails> page) {
        logger.info("【Hbase-core6】进入敏感通话详单(core6)分页查询方法");
        if (userId == null || phone == null) {
            logger.warn("【Hbase-core6】敏感通话详单(core6)分页查询方法入参存在空值,userId={},phone={}", userId, phone);
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(" select userId,phone,otherPhone,firstCall,lastCall,inTimes," +
                "outTimes,inDuration,outDuration," + "inFee,outFee,allTimes,callLocation,type,platform,commType from "
                + "extralDetails " + "where userId = ").append(userId);
        //拼接电话
        stringBuffer.append(" and phone = '").append(phone).append("' ");
        //拼接灵活参数
        if (extraParamter != null && !"".equals(extraParamter)) {
            stringBuffer.append(extraParamter);
        }
        //进行分页参数匹配
        String sql = stringBuffer.toString();
        if (page == null) {
            logger.warn("【Hbase-core6】敏感通话详单(core6)分页查询page为空");
        } else {
            sql = sql + " limit " + page.getPageSize() * page.getPageNo();
        }
        logger.info("【Hbase-core6】敏感通话详单(core6)分页查询方法,sql={}", sql);
        List<SolrExtraDetails> solrExtraDetailsList = new ArrayList<>();
        //如果hbase故障,直接返回 阻绝再向队列中发起请求
        // if (!HBASE_ACTIVE) {
        //   logger.error("[queryExtraDetails]===hbase故障,直接返回 阻绝请求积压,不再向队列中发起请求");
        //   return page;
        // }
        try {
            long startTime = System.currentTimeMillis();
            logger.info("【Hbase-core6】敏感通话详单(core6)开始分页查询结果");
            solrExtraDetailsList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SolrExtraDetails.class));
            logger.warn("【HBase】执行分页查询Result（userLocation）成功，sql:" + sql);
            logger.info("【Hbase-core6】敏感通话详单(core6)开始分页查询总页数");
            int totalCount = count(userId, phone);
            logger.info("【Hbase-core6】敏感通话详单(core6)开始分页查询总页数");
            long endTime = System.currentTimeMillis();
            float excTime = (float) (endTime - startTime) / 1000;
            //分页代码
            int pageSize = page.getPageSize();
            int pageNo = page.getPageNo();
            // 第PageNo页 起始位置
            //此处分页逻辑为：如果pageNo*pageSize已然大于总记录数,那么依然取最后一页的内容
            int pageRemoveNum = 0;
            if (totalCount > 0) {
                pageRemoveNum = (pageNo - 1) * pageSize < totalCount ? (pageNo - 1) * pageSize :
                        (totalCount - pageSize > 0 ? totalCount : 0);
            }
            solrExtraDetailsList = solrExtraDetailsList.subList(pageRemoveNum, solrExtraDetailsList.size());
            page.setTotalCount(totalCount);
            page.setResult(solrExtraDetailsList);
            logger.warn("【Hbase-core6】敏感通话详单(core6)执行分页查询成功，sql:" + sql + "，耗时：" + excTime);
            return page;
        } catch (Exception e) {
            logger.error("【Hbase-core6】敏感通话详单(core6)执行分页查询失败，sql:" + sql + "，失败原因:" + e);
            e.printStackTrace();
            //发生异常 则将空的集合返回出去
            assert page != null;
            page.setResult(solrExtraDetailsList);
            return page;
        }
    }

    @Override
    public int count(Long userId, String phone) {
        logger.info("进入【Hbase-core6】敏感通话详单(core6)计数方法体");
        if (userId == null || phone == null) {
            logger.warn("【Hbase-core6】敏感通话详单(core6)中存在空值userId={},phone={}", userId, phone);
            return 0;
        }
        String sql = " select count(1) from extralDetails  where userId = " + userId + " and phone = '" + phone + "' ";
        //如果hbase故障,直接返回 阻绝再向队列中发起请求
        // if (!HBASE_ACTIVE) {
        //   logger.error("[count]===hbase故障,直接返回 阻绝请求积压,不再向队列中发起请求");
        //   return 0;
        // }
        try {
            logger.info("开始执行【Hbase-core6】敏感通话详单(core6)查询count,sql={}", sql);
            int totalCount = jdbcTemplate.queryForObject(sql, Integer.class);
            logger.info("执行【Hbase-core6】敏感通话详单(core6)查询count成功,sql={},totalCount={}", sql, totalCount);
            return totalCount;
        } catch (Exception e) {
            logger.warn("执行【Hbase-core6】敏感通话详单(core6)查询count失败");
            e.printStackTrace();
        }

        return 0;
    }

    // 正则表达式 新增时 去除特殊字符串
    private String StringFilter(String str) throws PatternSyntaxException {
        if (str == null) {
            str = "";
        }
        String regEx = "[`~!@#$%^&()+=|{}':;',//[//].<>/?~！@#￥%……&（）——+|{}【】‘；：”“’。，、？\\\\]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
