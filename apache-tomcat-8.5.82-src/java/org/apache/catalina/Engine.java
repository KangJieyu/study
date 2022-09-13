/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina;

/**
 * An <b>Engine</b> is a Container that represents the entire Catalina servlet
 * engine.  It is useful in the following types of scenarios:
 * Engine 代表整个 Catalina servlet 引擎的容器。它应用于以下的场景类型：
 * <ul>
 * <li>You wish to use Interceptors that see every single request processed
 *     by the entire engine.
 *     你希望使用 Interceptors(拦截器)查看整个引擎处理的每个请求。
 * <li>You wish to run Catalina in with a standalone HTTP connector, but still
 *     want support for multiple virtual hosts.
 *     你希望在一个独立的 HTTP 连接器运行 Catalina ，但是仍然想支持多个虚拟主机。
 * </ul>
 * In general, you would not use an Engine when deploying Catalina connected
 * to a web server (such as Apache), because the Connector will have
 * utilized the web server's facilities to determine which Context (or
 * perhaps even which Wrapper) should be utilized to process this request.
 * 总体而言，当部署 Catalina 连接 web 服务器时不能使用 Engine ，因为 Connector 将
 * 利用 web 服务器的基础来确定使用的 Context(Wrapper) 来处理这个请求。
 * <p>
 * The child containers attached to an Engine are generally implementations
 * of Host (representing a virtual host) or Context (representing individual
 * an individual servlet context), depending upon the Engine implementation.
 * 附加到 Engine 的子容器一般是 Host(代表虚拟主机)或者 Context(代表独立的 servlet 上
 * 下文)，取决于 Engine 的实现。
 * <p>
 * If used, an Engine is always the top level Container in a Catalina
 * hierarchy. Therefore, the implementation's <code>setParent()</code> method
 * should throw <code>IllegalArgumentException</code>.
 * 如果被使用，在 Catalina 层次中 Engine 是一个高级别的 Container。因此，使用 setParent()
 * 方法会抛出 IllegalArgumentException 异常。
 *
 * @author Craig R. McClanahan
 */
public interface Engine extends Container {

    /**
     * @return the default host name for this Engine.
     */
    public String getDefaultHost();


    /**
     * Set the default hostname for this Engine.
     *
     * @param defaultHost The new default host
     */
    public void setDefaultHost(String defaultHost);


    /**
     * @return the JvmRouteId for this engine.
     */
    public String getJvmRoute();


    /**
     * Set the JvmRouteId for this engine.
     *
     * @param jvmRouteId the (new) JVM Route ID. Each Engine within a cluster
     *        must have a unique JVM Route ID.
     */
    public void setJvmRoute(String jvmRouteId);


    /**
     * @return the <code>Service</code> with which we are associated (if any).
     */
    public Service getService();


    /**
     * Set the <code>Service</code> with which we are associated (if any).
     *
     * @param service The service that owns this Engine
     */
    public void setService(Service service);
}
