package com.wxtest.demo.common.hbase.IHbase;

import com.wxtest.demo.entity.SolrCallDetails;

import java.util.List;

/**
 * core2 SolrCallDetails 定时任务 ScheduledTasks addNewMoneAndDeleteRepayAndMthree 1070 行  query(solrQuery,
 * SolrCallDetailsSimple.class)方法
 *
 * @author EVE
 */
public interface HBaseSolrCallDetailsDao {

    List<SolrCallDetails> query(SolrCallDetails solrCallDetails);

}
