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

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 * Encodes higher-level message objects into binary or protocol-specific data.
 * MINA invokes {@link #encode(IoSession, Object, ProtocolEncoderOutput)}
 * method with message which is popped from the session write queue, and then
 * the encoder implementation puts encoded messages (typically {@link IoBuffer}s)
 * into {@link ProtocolEncoderOutput} by calling {@link ProtocolEncoderOutput#write(Object)}.
 * <p>
 * Please refer to
 * <a href="../../../../../xref-examples/org/apache/mina/examples/reverser/TextLineEncoder.html"><code>TextLineEncoder</code></a>
 * example.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 * 
 * @see ProtocolEncoderException
 */
public interface ProtocolEncoder {

    /**
     * Encodes higher-level message objects into binary or protocol-specific data.
     * MINA invokes {@link #encode(IoSession, Object, ProtocolEncoderOutput)}
     * method with message which is popped from the session write queue, and then
     * the encoder implementation puts encoded messages (typically {@link IoBuffer}s)
     * into {@link ProtocolEncoderOutput}.
     *
     * @param session The current Session
     * @param message the message to encode
     * @param out The {@link ProtocolEncoderOutput} that will receive the encoded message
     * @throws Exception if the message violated protocol specification
     */
    void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception;

    /**
     * Releases all resources related with this encoder.
     *
     * @param session The current Session
     * @throws Exception if failed to dispose all resources
     */
    void dispose(IoSession session) throws Exception;
}