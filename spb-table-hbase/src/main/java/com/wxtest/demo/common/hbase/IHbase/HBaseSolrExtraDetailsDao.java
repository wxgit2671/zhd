package com.wxtest.demo.common.hbase.IHbase;

import com.wxtest.demo.entity.SolrExtraDetails;
import com.wxtest.demo.utils.Page;

import java.util.Collection;

/**
 * core6 敏感电话详单
 * 其中queryExtraDetails方法 extraParamter 参数为 自由拼接的参数串
 * 例如 and a = XXX 或者 order by a desc b asc 请确保sql准确性 以及 拼接字段前空格
 * Created by 孟庆艺 on 2017-07-06.
 */
public interface HBaseSolrExtraDetailsDao {

  //根据userId phone 删除
  void deleteExtraDetails(Long userId, String phone);

  //添加信息
  void addExtraDetails(Collection<SolrExtraDetails> extraDetailsList);

  //分页查询 extraParamter 参数为 自由拼接的参数串 例如 and a = XXX 或者 order by a desc b asc 请确保sql准确性 以及 拼接字段前空格
  Page<SolrExtraDetails> queryExtraDetails(Long userId, String phone, String extraParamter,
          Page<SolrExtraDetails> page);

  int count(Long userId, String phone);
  
      /**
     * @param userId
     * @param phone
     * @return
     * GY
     * 2017年8月30日
     * 导数
     */
    boolean findExtraDetails(Long userId, String phone, String sensitivityPhone);

}
