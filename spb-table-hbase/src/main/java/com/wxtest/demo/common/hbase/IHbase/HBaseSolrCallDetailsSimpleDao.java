package com.wxtest.demo.common.hbase.IHbase;

import com.wxtest.demo.entity.SolrCallDetailsSimple;
import com.wxtest.demo.utils.Page;

import java.util.Collection;
import java.util.List;

/**
 * cms->ScheduledTask->query(solrQuery, SolrCallDetailsSimple.class) 1070行 ..
 * UserCallRecordsService->query(query, SolrCallDetailsSimple.class, page) 46行
 * 
 * @author EVE
 *
 */
public interface HBaseSolrCallDetailsSimpleDao {

    List<SolrCallDetailsSimple> query(SolrCallDetailsSimple solrCallDetailsSimple);

    Page<SolrCallDetailsSimple> query(SolrCallDetailsSimple solrCallDetailsSimple, Page<SolrCallDetailsSimple> page);

    void deleteByCondition(SolrCallDetailsSimple solrCallDetailsSimple);

    void add(Collection<SolrCallDetailsSimple> beans);
    
    List<SolrCallDetailsSimple> query(String s);

    List<SolrCallDetailsSimple> queryNotLimit(SolrCallDetailsSimple solrCallDetailsSimple);

}
