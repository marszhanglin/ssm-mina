/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.filter.keepalive;

import org.apache.mina.core.session.IoSession;

/**
 * Provides keep-alive messages to {@link KeepAliveFilter}.
 *  
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public interface KeepAliveMessageFactory {

    /**
     * @return <tt>true</tt> if and only if the specified message is a
     * keep-alive request message.
     * 
     * @param session The current session
     * @param message teh message to check
     */
    boolean isRequest(IoSession session, Object message);

    /**
     * @return <tt>true</tt> if and only if the specified message is a 
     * keep-alive response message;
     * 
     * @param session The current session
     * @param message teh message to check
     */
    boolean isResponse(IoSession session, Object message);

    /**
     * @return a (new) keep-alive request message or <tt>null</tt> if no request is required.
     * 
     * @param session The current session
     */
    Object getRequest(IoSession session);

    /**
     * @return a (new) response message for the specified keep-alive request, or <tt>null</tt> if no response is required.
     * 
     * @param session The current session
     * @param request The request we are lookig for
     */
    Object getResponse(IoSession session, Object request);
}
