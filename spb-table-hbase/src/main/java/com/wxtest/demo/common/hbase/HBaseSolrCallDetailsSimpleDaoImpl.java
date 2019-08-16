package com.wxtest.demo.common.hbase;

import com.google.common.collect.Lists;

import com.wxtest.demo.common.hbase.IHbase.HBaseSolrCallDetailsSimpleDao;
import com.wxtest.demo.dao.HbaseContactsDao;
import com.wxtest.demo.entity.SolrCallDetailsSimple;
import com.wxtest.demo.utils.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import static com.wxtest.demo.common.hbase.HbaseActiveStatus.HBASE_ACTIVE;

@Repository
public class HBaseSolrCallDetailsSimpleDaoImpl implements HBaseSolrCallDetailsSimpleDao {

    private static Logger logger = LoggerFactory.getLogger("log.hbase.HBaseSolrCallDetailsSimpleDaoImpls");

    @Autowired
    @Qualifier("phoenixJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HbaseContactsDao hbaseContactsDao;

    /* (non-Javadoc)
     * @see com.mimidai.common.dao.hbase.HBaseSolrCallDetailsSimpleDao#query(java.lang.String)
     * GY
     * sql查询List<SolrCallDetailsSimple>
     */
    @Override
    public List<SolrCallDetailsSimple> query(String sql) {
        List<SolrCallDetailsSimple> solrCallDetailsSimpleList;
        // try {
        //     long startTime = System.currentTimeMillis();
        //     solrCallDetailsSimpleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SolrCallDetailsSimple>
        //             (SolrCallDetailsSimple.class));
        //     long endTime = System.currentTimeMillis();
        //     float excTime = (float) (endTime - startTime) / 1000;
        //     logger.warn("【HBase】执行查询(query)成功（core5），sql:" + sql + "，耗时：" + excTime);
        //     if (sql.indexOf("userId") == -1) { return null; }
        //     return solrCallDetailsSimpleList;
        // } catch (Exception e) {
        //     logger.error("【HBase】执行查询(query)失败（core5），sql:" + sql + "，失败原因:" + e.toString(), e);
        //     e.printStackTrace();
        // }
        return null;
    }


    /**
     * CMS调用 暂时做降级处理
     */
    @Override
    public List<SolrCallDetailsSimple> query(SolrCallDetailsSimple solrCallDetailsSimple) {
        logger.warn("【HBase】进入查询（core5）方法");
        Long userId = solrCallDetailsSimple.getUserId();
        String phone1 = solrCallDetailsSimple.getPhone();
        String otherPhone1 = solrCallDetailsSimple.getOtherPhone();
        if (solrCallDetailsSimple == null || (userId == null && StringUtils.isBlank(phone1) && StringUtils.isBlank
                (otherPhone1))) {
            logger.error("【HBase】（core5）入参solrCallDetailsSimple为NULL或一个参数都未传");
            return null;
        }
        List<SolrCallDetailsSimple> solrCallDetailsSimpleList = Lists.newArrayList();
        if (HBASE_ACTIVE) {
            StringBuffer sb = new StringBuffer(" select userId,phone,otherPhone,inTimes,inFee,inDuration,outTimes, "
                    + "outFee,outDuration,firstCall,lastCall,allTimes,commType,callLocation from contacts where 1=1 ");
            if (userId != null) {
                sb.append(" and userId = ").append(userId);
            } else {
                return null;
            }
            if (StringUtils.isNotBlank(phone1)) {
                String phone = "'" + phone1 + "'";
                sb.append(" and  phone =  ").append(phone);
            }
            if (StringUtils.isNotBlank(otherPhone1)) {
                String otherPhone = "'" + otherPhone1 + "'";
                sb.append(" and otherPhone = ").append(otherPhone);
            }
            sb.append(" order by allTimes desc limit 800  ");
            String sql = sb.toString();
            logger.warn("【HBase】进入查询（core5）方法，sql={}", sql);
            try {
                long startTime = System.currentTimeMillis();
                solrCallDetailsSimpleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SolrCallDetailsSimple
                        .class));
                long endTime = System.currentTimeMillis();
                float excTime = (float) (endTime - startTime) / 1000;
                logger.warn("【HBase】执行查询(query)成功（core5），sql:" + sql + "，耗时：" + excTime);
                if (sql.indexOf("userId") == -1) { return null; }
                return solrCallDetailsSimpleList;
            } catch (Exception e) {
                logger.error("【HBase】执行查询(query)失败（core5），sql:" + sql + "，失败原因:" + e.toString(), e);
                e.printStackTrace();
                return solrCallDetailsSimpleList;
            }
        } else {
            logger.warn("[hbase-contacts临时方案]进入查询（core5）方法");
            long startTime = System.currentTimeMillis();
            solrCallDetailsSimpleList = hbaseContactsDao.query(userId, phone1, Boolean.FALSE);
            long endTime = System.currentTimeMillis();
            long excTime = endTime - startTime;
            logger.info("[hbase-contacts临时方案]执行查询用户userId{},phone{}成功,耗时：{}", userId, phone1, excTime);
            return solrCallDetailsSimpleList;
        }
    }

    /**
     * CMS调用,暂时做降级处理 直接返回空
     */
    @Override
    public Page<SolrCallDetailsSimple> query(SolrCallDetailsSimple solrCallDetailsSimple,
            Page<SolrCallDetailsSimple> page) {
        logger.warn("【HBase】进入分页查询（core5）方法");
        Long userId = solrCallDetailsSimple.getUserId();
        String phone1 = solrCallDetailsSimple.getPhone();
        if (solrCallDetailsSimple == null || page == null || (userId == null && StringUtils.isBlank(phone1))) {
            logger.error("【HBase】（core5）入参solrCallDetailsSimple或page为NULL或一个参数都未传");
            return null;
        }
        if (HBASE_ACTIVE) {
            String sql = "";
            String pageSql = "select count(1) from  contacts where 1=1 ";
            StringBuffer sb = new StringBuffer(" select userId,phone,otherPhone,inTimes,inFee,inDuration,outTimes," +
                    "outFee,outDuration,firstCall, lastCall,allTimes,commType,callLocation from contacts where  1=1  ");
            if (userId != null) {
                sb.append(" and userId = ").append(userId);
                pageSql += " and userId = " + userId;
            }
            if (StringUtils.isNotBlank(phone1)) {
                String phone = "'" + phone1 + "'";
                sb.append(" and  phone =  ").append(phone);
                pageSql += " and phone =" + phone;
            }
            if (StringUtils.isNotBlank(page.getOrderBy()) && StringUtils.isNotBlank(page.getOrderDir())) {
                sb.append(" order by ");
                List<Page.Sort> sortList = page.getSort();
                for (Page.Sort sort : sortList) {
                    sb.append(sort.getProperty() + " " + sort.getDir() + ",");
                }
                sql = sb.substring(0, sb.length() - 1);
            }
            // sql = sql + " limit " + page.getPageSize() + " offset " + page.getOffset();
            // 分页暂时通过查询 pageSize*pageNo 查询到结果集后再在逻辑中 remove不需要的记录
            sql = sql + " limit " + page.getPageSize() * page.getPageNo();
            logger.warn("【HBase】进入分页查询（core5）方法，sql={}", sql);
            try {
                long startTime = System.currentTimeMillis();
                logger.warn("【HBase】执行分页查询Result（query）成功（core5），sql:" + sql);
                List<SolrCallDetailsSimple> solrCallDetailsSimpleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SolrCallDetailsSimple.class));
                logger.warn("【HBase】执行分页查询pageSize（query）成功（core5），sql:" + pageSql);
                long totalCount = jdbcTemplate.queryForObject(pageSql, Long.class);
                long endTime = System.currentTimeMillis();
                float excTime = (float) (endTime - startTime) / 1000;
                page.setTotalCount(totalCount);
                page.setResult(solrCallDetailsSimpleList);
                logger.warn("【HBase】执行分页查询（query）成功（core5），sql:" + sql + "，耗时：" + excTime);
                return page;
            } catch (Exception e) {
                logger.error("【HBase】执行分页查询（query）失败（core5），sql:" + sql + "，失败原因:" + e.toString(), e);
                e.printStackTrace();
                return null;
            }
        } else {
            logger.warn("[hbase-contacts临时方案]进入查询方法");
            long startTime = System.currentTimeMillis();
            List<SolrCallDetailsSimple> query = hbaseContactsDao.query(userId, phone1, Boolean.TRUE);
            if (query.isEmpty()) {
                page.setTotalCount(0);
            } else {
                page.setTotalCount(query.size());
                page.setResult(query);
            }
            long endTime = System.currentTimeMillis();
            long excTime = endTime - startTime;
            logger.info("[hbase-contacts临时方案]执行删除用户userId{},phone{}成功,耗时：{}", userId, phone1, excTime);
            return page;
        }
    }

    @Override
    public void deleteByCondition(SolrCallDetailsSimple solrCallDetailsSimple) {
        logger.warn("[hbase-contacts临时方案]进入删除（core5）方法");
        Long userId = solrCallDetailsSimple.getUserId();
        String phone = solrCallDetailsSimple.getPhone();
        if (solrCallDetailsSimple == null || userId == null || StringUtils.isBlank(phone)) {
            logger.error("[hbase-contacts临时方案]入参为NULL");
            return;
        }
        if (HBASE_ACTIVE) {
            String sql = "delete from contacts where userId = " + solrCallDetailsSimple.getUserId() + " and phone= '"
                    + solrCallDetailsSimple.getPhone() + "'";
            logger.warn("【HBase】进入删除（core5）方法，sql={}", sql);
            try {
                long startTime = System.currentTimeMillis();
                jdbcTemplate.execute(sql);
                long endTime = System.currentTimeMillis();
                float excTime = (float) (endTime - startTime) / 1000;
                logger.info("【HBase】执行删除（deleteByCondition）成功（core5），sql:" + sql + "，耗时：" + excTime);
            } catch (Exception e) {
                logger.error("【HBase】执行删除（deleteByCondition）失败（core5），sql:" + sql + "，失败原因:" + e.toString(), e);
                e.printStackTrace();
            }
        } else {
            long startTime = System.currentTimeMillis();
            hbaseContactsDao.deleteByCondition(userId, phone);
            long endTime = System.currentTimeMillis();
            long excTime = endTime - startTime;
            logger.info("[hbase-contacts临时方案]执行删除用户userId{},phone{}成功,耗时：{}", userId, phone, excTime);
        }
    }

    @Override
    public void add(Collection<SolrCallDetailsSimple> beans) {
        logger.warn("【HBase】进入批量插入（core5）方法");
        if (CollectionUtils.isEmpty(beans)) {
            logger.error("【HBase】（core5）入参beans为NULL");
            return;
        }
        // map.value - > beans
        // 其实是将 map(otherPhone,SolrCallDetailsSimple) 转成 一个 实体类集合的Collection<SolrCallDetailsSimple>
        // 获取集合长度
        final int size = beans.size();// 其实应该写实体类的属性数量
        List<SolrCallDetailsSimple> solrCallDetailsSimplesList = new ArrayList<>(beans);
        String sql = "upsert into contacts VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if (HBASE_ACTIVE) {
            logger.warn("【HBase】进入批量插入（core5）方法，user_id={},sql={}", solrCallDetailsSimplesList.get(0).getUserId(), sql);
            // 创建一个 实体类长度的数组
            Object args[] = new Object[15];
            long startTime = System.currentTimeMillis();
            try {
                for (int i = 0; i < size; i++) {
                    try {
                        // 设置完入参
                        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
                        args[0] = uuid;
                        args[1] = solrCallDetailsSimplesList.get(i).getUserId();
                        args[2] = StringFilter(solrCallDetailsSimplesList.get(i).getPhone());
                        args[3] = StringFilter(solrCallDetailsSimplesList.get(i).getOtherPhone());
                        args[4] = StringFilter(solrCallDetailsSimplesList.get(i).getFirstCall());
                        args[5] = StringFilter(solrCallDetailsSimplesList.get(i).getLastCall());
                        args[6] = solrCallDetailsSimplesList.get(i).getInTimes();
                        args[7] = solrCallDetailsSimplesList.get(i).getOutTimes();
                        args[8] = solrCallDetailsSimplesList.get(i).getInDuration();
                        args[9] = solrCallDetailsSimplesList.get(i).getOutDuration();
                        args[10] = solrCallDetailsSimplesList.get(i).getInFee();
                        args[11] = solrCallDetailsSimplesList.get(i).getOutFee();
                        args[12] = solrCallDetailsSimplesList.get(i).getAllTimes();
                        args[13] = StringFilter(solrCallDetailsSimplesList.get(i).getCallLocation());
                        args[14] = StringFilter(solrCallDetailsSimplesList.get(i).getCommType());
                        jdbcTemplate.update(sql, args);
                    } catch (Exception e) {
                        logger.error("【HBase】执行新增（add）失败（core5），user_id=" + args[0] + "，sql:" + sql + "，失败原因:" + e
                                .toString(), e);
                        e.printStackTrace();
                    }
                }
                long endTime = System.currentTimeMillis();
                float excTime = (float) (endTime - startTime) / 1000;
                try {
                    logger.warn("【HBase】进入批量插入（core5）方法，user_id={},size={},耗时={}", solrCallDetailsSimplesList.get(0)
                                                                                                             .getUserId(), solrCallDetailsSimplesList.size(), excTime);
                } catch (Exception e) {
                    logger.error("添加日志错误", e);
                }
            } catch (Exception e) {
                logger.error("【HBase】core5 add for循环异常：", e);
                e.printStackTrace();
            }
        } else {
            // 创建一个 实体类长度的数组
            long startTime = System.currentTimeMillis();
            hbaseContactsDao.add(solrCallDetailsSimplesList);
            long endTime = System.currentTimeMillis();
            long excTime = endTime - startTime;
            logger.info("[hbase-contacts临时方案]用户userId={}插入到mysql数据库耗时{}ms", solrCallDetailsSimplesList.get(0)
                                                                                                      .getUserId(),
                    excTime);
        }
    }

    // 正则表达式 新增时 去除特殊字符串
    public String StringFilter(String str) throws PatternSyntaxException {
        if (str == null) {
            str = "";
        }
        String regEx = "[`~!@#$%^&()+=|{}':;',//[//].<>/?~！@#￥%……（）——+|{}【】‘；：”“’。，、？\\\\]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    @Override
    public List<SolrCallDetailsSimple> queryNotLimit(SolrCallDetailsSimple solrCallDetailsSimple) {
        logger.warn("【HBase】进入查询（core5）方法");
        Long userId = solrCallDetailsSimple.getUserId();
        String phone1 = solrCallDetailsSimple.getPhone();
        String otherPhone1 = solrCallDetailsSimple.getOtherPhone();
        if (solrCallDetailsSimple == null || (userId == null && StringUtils.isBlank(phone1) && StringUtils.isBlank
                (otherPhone1))) {
            logger.error("【HBase】（core5）入参solrCallDetailsSimple为NULL或一个参数都未传");
            return null;
        }
        List<SolrCallDetailsSimple> solrCallDetailsSimpleList = Lists.newArrayList();
        if (HBASE_ACTIVE) {
            StringBuffer sb = new StringBuffer(" select userId,phone,otherPhone,inTimes,inFee,inDuration,outTimes, "
                    + "outFee,outDuration,firstCall,lastCall,allTimes,commType,callLocation from contacts where 1=1 ");
            if (userId != null) {
                sb.append(" and userId = ").append(userId);
            } else {
                return null;
            }
            if (StringUtils.isNotBlank(phone1)) {
                String phone = "'" + phone1 + "'";
                sb.append(" and  phone =  ").append(phone);
            }
            if (StringUtils.isNotBlank(otherPhone1)) {
                String otherPhone = "'" + otherPhone1 + "'";
                sb.append(" and otherPhone = ").append(otherPhone);
            }
            //sb.append(" order by allTimes desc limit 800  ");
            sb.append(" order by allTimes desc  ");
            String sql = sb.toString();
            logger.warn("【HBase】进入查询（core5）方法，sql={}", sql);
            try {
                long startTime = System.currentTimeMillis();
                solrCallDetailsSimpleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SolrCallDetailsSimple
                        .class));
                long endTime = System.currentTimeMillis();
                float excTime = (float) (endTime - startTime) / 1000;
                logger.warn("【HBase】执行查询(query)成功（core5），sql:" + sql + "，耗时：" + excTime);
                if (sql.indexOf("userId") == -1) { return null; }
                return solrCallDetailsSimpleList;
            } catch (Exception e) {
                logger.error("【HBase】执行查询(query)失败（core5），sql:" + sql + "，失败原因:" + e.toString(), e);
                e.printStackTrace();
                return solrCallDetailsSimpleList;
            }
        } else {
            logger.warn("[hbase-contacts临时方案]进入查询（core5）方法");
            long startTime = System.currentTimeMillis();
            solrCallDetailsSimpleList = hbaseContactsDao.query(userId, phone1, Boolean.FALSE);
            long endTime = System.currentTimeMillis();
            long excTime = endTime - startTime;
            logger.info("[hbase-contacts临时方案]执行查询用户userId{},phone{}成功,耗时：{}", userId, phone1, excTime);
            return solrCallDetailsSimpleList;
        }
    }
}
