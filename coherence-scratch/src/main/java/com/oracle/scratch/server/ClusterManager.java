/*
 * File: ClusterManager.java
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * The contents of this file are subject to the terms and conditions of
 * the Common Development and Distribution License 1.0 (the "License").
 *
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the License by consulting the LICENSE.txt file
 * distributed with this file, or by consulting
 * or https://oss.oracle.com/licenses/CDDL
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file LICENSE.txt.
 *
 * MODIFICATIONS:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 */

package com.oracle.scratch.server;

import com.oracle.tools.runtime.PropertiesBuilder;

import com.oracle.tools.runtime.coherence.Cluster;
import com.oracle.tools.runtime.coherence.ClusterBuilder;
import com.oracle.tools.runtime.coherence.ClusterMember;
import com.oracle.tools.runtime.coherence.ClusterMemberSchema;

import com.oracle.tools.runtime.console.SystemApplicationConsole;

import com.oracle.tools.runtime.java.ExternalJavaApplicationBuilder;
import com.oracle.tools.runtime.java.VirtualizedJavaApplicationBuilder;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: narliss
 * Date: 2/15/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClusterManager
{
    private int     m_serverCount     = 1;
    private int     m_proxyCount      = 0;
    private int     m_managementCount = 1;
    private int     m_clusterPort;
    private int     m_proxyPort;
    private int     m_JMXPort;
    private String  m_serverConfig;
    private String  m_proxyConfig;
    private String  m_managementConfig;
    private String  m_siteName;
    private Cluster m_cluster;


    /**
     * Constructs ...
     *
     *
     * @param serverCount
     * @param proxyCount
     * @param managementCount
     * @param clusterPort
     * @param proxyPort
     * @param JMXPort
     * @param serverConfig
     * @param proxyConfig
     * @param managementConfig
     * @param siteName
     */
    public ClusterManager(int    serverCount,
                          int    proxyCount,
                          int    managementCount,
                          int    clusterPort,
                          int    proxyPort,
                          int    JMXPort,
                          String serverConfig,
                          String proxyConfig,
                          String managementConfig,
                          String siteName)
    {
        m_serverCount      = serverCount;
        m_proxyCount       = proxyCount;
        m_managementCount  = managementCount;
        m_clusterPort      = clusterPort;
        m_proxyPort        = proxyPort;
        m_JMXPort          = JMXPort;

        m_serverConfig     = serverConfig;
        m_proxyConfig      = proxyConfig;
        m_managementConfig = managementConfig;
        m_siteName         = siteName;
    }

    /**
     * Method description
     *
     * @return
     */
    public int getServerCount()
    {
        return m_serverCount;
    }


    /**
     * Method description
     *
     * @param count
     */
    public void setServerCount(int count)
    {
        m_serverCount = count;
    }


    /**
     * Method description
     *
     * @return
     */
    public int getProxyCount()
    {
        return m_proxyCount;
    }


    /**
     * Method description
     *
     * @param count
     */
    public void setProxyCount(int count)
    {
        m_proxyCount = count;
    }


    /**
     * Method description
     *
     * @return
     */
    public int getManagementCount()
    {
        return m_managementCount;
    }


    /**
     * Method description
     *
     * @param count
     */
    public void setManagementCount(int count)
    {
        m_managementCount = count;
    }


    /**
     * Method description
     *
     * @return
     */
    public int getClusterPort()
    {
        return m_clusterPort;
    }


    /**
     * Method description
     *
     * @param port
     */
    public void setClusterPort(int port)
    {
        m_clusterPort = port;
    }


    /**
     * Method description
     *
     * @return
     */
    public String getSiteName()
    {
        return m_siteName;
    }


    /**
     * Method description
     *
     * @param siteName
     */
    public void setSiteName(String siteName)
    {
        m_siteName = siteName;
    }


    /**
     * Method description
     *
     * @return
     */
    public String getServerConfig()
    {
        return m_serverConfig;
    }


    /**
     * Method description
     *
     * @param serverConfig
     */
    public void setServerConfig(String serverConfig)
    {
        m_serverConfig = serverConfig;
    }


    /**
     * Method description
     *
     * @return
     */
    public String getProxyConfig()
    {
        return m_proxyConfig;
    }


    /**
     * Method description
     *
     * @param proxyConfig
     */
    public void setProxyConfig(String proxyConfig)
    {
        m_proxyConfig = proxyConfig;
    }


    /**
     * Method description
     *
     * @return
     */
    public String getManagementConfig()
    {
        return m_managementConfig;
    }


    /**
     * Method description
     *
     * @param managementConfig
     */
    public void setManagementConfig(String managementConfig)
    {
        m_managementConfig = managementConfig;
    }


    /**
     * Method description
     *
     * @return
     */
    public Cluster getCluster()
    {
        return m_cluster;
    }


    /**
     * Method description
     *
     * @param cluster
     */
    public void setCluster(Cluster cluster)
    {
        m_cluster = cluster;
    }

    /**
     * Method description
     *
     * @return
     */
    public int getJMXPort()
    {
        return m_JMXPort;
    }


    /**
     * Method description
     *
     * @param port
     */
    public void setJMXPort(int port)
    {
        m_JMXPort = port;
    }

    /**
     * Method description
     *
     * @return
     */
    public ClusterMemberSchema getBaseSchema()
    {
        return new ClusterMemberSchema().setClusterPort(getClusterPort())
                .setSiteName(getSiteName())
                .setEnvironmentVariables(PropertiesBuilder.fromCurrentEnvironmentVariables())
                .setSystemProperties(PropertiesBuilder.fromCurrentSystemProperties());
    }


    /**
     * Method description
     */
    public void startCluster()
    {
        ClusterBuilder builder = new ClusterBuilder();

        // Setup the server schema
        ClusterMemberSchema serverSchema = getBaseSchema();

        serverSchema.setCacheConfigURI(getServerConfig())
            .setJMXManagementMode(ClusterMemberSchema.JMXManagementMode.REMOTE_ONLY)
            .setRoleName("Server");

        // Setup the proxy schema
        ClusterMemberSchema proxySchema = getBaseSchema();

        proxySchema.setCacheConfigURI(getProxyConfig()).setStorageEnabled(false)
            .setJMXManagementMode(ClusterMemberSchema.JMXManagementMode.REMOTE_ONLY)
            .setRoleName("Proxy")
            .setJMXPort(m_proxyPort);

        // Setup the management schema
        ClusterMemberSchema managementSchema = getBaseSchema();

        managementSchema.setCacheConfigURI(getManagementConfig()).setStorageEnabled(false)
            .setJMXManagementMode(ClusterMemberSchema.JMXManagementMode.ALL).setJMXAuthentication(false)
            .setJMXPort(getJMXPort()).setJMXSupport(true).setRemoteJMXManagement(true).setRoleName("Management");

        builder.addBuilder(new ExternalJavaApplicationBuilder<ClusterMember, ClusterMemberSchema>(),
                           serverSchema,
                           "Server",
                           getServerCount());

        builder.addBuilder(new ExternalJavaApplicationBuilder<ClusterMember, ClusterMemberSchema>(),
                           proxySchema,
                           "Proxy",
                           getProxyCount());

        builder.addBuilder(new ExternalJavaApplicationBuilder<ClusterMember, ClusterMemberSchema>(),
                           managementSchema,
                           "Management",
                           getManagementCount());

        try
        {
            setCluster(builder.realize(new SystemApplicationConsole()));
            assert(getCluster().getClusterSize() == getServerCount() + getProxyCount() + getManagementCount());
        }
        catch (IOException e)
        {
            e.printStackTrace();    // To change body of catch statement use File | Settings | File Templates.
            getCluster().destroy();
        }
    }


    /**
     * Method description
     */
    public void stopCluster()
    {
        getCluster().destroy();
    }
}