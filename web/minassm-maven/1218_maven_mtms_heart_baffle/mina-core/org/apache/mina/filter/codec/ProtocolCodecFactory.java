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
package org.apache.mina.filter.codec;

import org.apache.mina.core.session.IoSession;

/**
 * Provides {@link ProtocolEncoder} and {@link ProtocolDecoder} which translates
 * binary or protocol specific data into message object and vice versa.
 * <p>
 * Please refer to
 * <a href="../../../../../xref-examples/org/apache/mina/examples/reverser/ReverseProtocolProvider.html"><code>ReverserProtocolProvider</code></a>
 * example.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public interface ProtocolCodecFactory {
    /**
     * Returns a new (or reusable) instance of {@link ProtocolEncoder} which
     * encodes message objects into binary or protocol-specific data.
     * 
     * @param session The current session
     * @return The encoder instance
     * @throws Exception If an error occurred while retrieving the encoder
     */
    ProtocolEncoder getEncoder(IoSession session) throws Exception;

    /**
     * Returns a new (or reusable) instance of {@link ProtocolDecoder} which
     * decodes binary or protocol-specific data into message objects.
     * 
     * @param session The current session
     * @return The decoder instance
     * @throws Exception If an error occurred while retrieving the decoder
     */
    ProtocolDecoder getDecoder(IoSession session) throws Exception;
}