/*
 * Copyright (c) 2013-2016 Cinchapi Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cinchapi.deepstack.binary;

import java.nio.ByteBuffer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * 
 * @author Jeff Nelson
 */
public class Person {

    /**
     * Given a buffer of {@code bytes}, construct a {@link Person} object that
     * contains the encoded data.
     * 
     * @param bytes
     * @return the {@link Person} encoded in the ByteBuffer
     */
    public static Person fromByteBuffer(ByteBuffer bytes) {
        return null;
    }

    /**
     * Return a {@link ByteBuffer} that contains all the information in this
     * class encoded in binary format.
     * 
     * @return a {@link ByteBuffer} for this object
     */
    public ByteBuffer toByteBuffer() {
        return null;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
