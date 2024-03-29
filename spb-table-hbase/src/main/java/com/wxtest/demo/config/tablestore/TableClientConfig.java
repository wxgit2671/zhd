package com.wxtest.demo.config.tablestore;

import com.alicloud.openservices.tablestore.ClientConfiguration;
import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.AlwaysRetryStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableClientConfig {
    private static Logger logger = LoggerFactory.getLogger("log.hbase.TableClientConfig");
    @Value("${table_client_end_point}")
    private String endPoint;
    @Value("${table_client_access_id}")
    private String accessId;
    @Value("${table_client_access_key}")
    private String accessKey;
    @Value("${table_client_instance_name}")
    private String instanceName;

    @Bean
    public SyncClient createClient() {
        try {
            logger.info("开始调用createClient方法");
            // ClientConfiguration提供了很多配置项，以下只列举部分。
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            // 设置建立连接的超时时间。
            clientConfiguration.setConnectionTimeoutInMillisecond(5000);
            // 设置socket超时时间。
            clientConfiguration.setSocketTimeoutInMillisecond(5000);
            // 设置重试策略，若不设置，采用默认的重试策略。
            clientConfiguration.setRetryStrategy(new AlwaysRetryStrategy());
            logger.info("表格存储cleint初始化成功");
            return new SyncClient(endPoint, accessId, accessKey, instanceName, clientConfiguration);
        } catch (Exception e) {
            logger.error("----------------------表格存储client初始化失败");
        }
        return null;

    }

}
