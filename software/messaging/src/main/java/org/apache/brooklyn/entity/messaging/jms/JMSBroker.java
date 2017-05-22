/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.brooklyn.entity.messaging.jms;

import java.util.Collection;
import java.util.Map;

import org.apache.brooklyn.config.ConfigKey;
import org.apache.brooklyn.core.config.ConfigKeys;
import org.apache.brooklyn.entity.messaging.MessageBroker;
import org.apache.brooklyn.entity.messaging.Queue;
import org.apache.brooklyn.entity.messaging.Topic;
import org.apache.brooklyn.entity.software.base.SoftwareProcess;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;

public interface JMSBroker<Q extends JMSDestination & Queue, T extends JMSDestination & Topic> extends SoftwareProcess, MessageBroker {

    @SuppressWarnings("serial")
    ConfigKey<Collection<String>> QUEUES = ConfigKeys.newConfigKey(
            new TypeToken<Collection<String>>() {},
            "queues",
            "",
            ImmutableList.<String>of());

    @SuppressWarnings("serial")
    ConfigKey<Collection<String>> TOPICS = ConfigKeys.newConfigKey(
            new TypeToken<Collection<String>>() {},
            "topics",
            "",
            ImmutableList.<String>of());

    ConfigKey<String> QUEUE = ConfigKeys.newStringConfigKey("queue", "[DEPRECATED; Use queues]");

    ConfigKey<String> TOPIC = ConfigKeys.newStringConfigKey("topic", "[DEPRECATED; Use topics]");

    public Collection<String> getQueueNames();
    
    @VisibleForTesting
    public Collection<String> getTopicNames();

    @VisibleForTesting
    public Map<String, Q> getQueues();
    
    @VisibleForTesting
    public Map<String, T> getTopics();
    
    /** TODO make this an effector */
    public void addQueue(String name);
    
    public void addQueue(String name, Map properties);

    public Q createQueue(Map properties);

    /** TODO make this an effector */
    public void addTopic(String name);
    
    public void addTopic(String name, Map properties);

    public T createTopic(Map properties);
}
