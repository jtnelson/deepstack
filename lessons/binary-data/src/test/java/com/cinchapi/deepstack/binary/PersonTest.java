package com.cinchapi.deepstack.binary;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;
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

import com.cinchapi.deepstack.binary.Person;

/**
 * Unit tests for the {@link Person} class.
 * 
 * @author Jeff Nelson
 */
public class PersonTest {

    @Test
    public void testToByteBuffer() {
        Person expected = new Person();
        expected.hasInternship = false;
        expected.fname = "John";
        expected.lname = "Doe";
        expected.age = 100;
        expected.friends.add("Jane Doe");
        expected.friends.add("Michael Jordan");
        expected.friends.add("Kanye West");
        expected.friends.add("Serena Williams");
        ByteBuffer bytes = expected.toByteBuffer();
        Person actual = Person.fromByteBuffer(bytes);
        Assert.assertEquals(expected, actual);
    }

}
