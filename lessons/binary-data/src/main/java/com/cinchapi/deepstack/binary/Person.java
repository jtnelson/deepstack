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
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * 
 * @author Jeff Nelson
 */
public class Person {

    byte age;
    String fname;
    String lname;
    boolean hasInternship;
    List<String> friends = new ArrayList<>();


    /**
     * Given a buffer of {@code bytes}, construct a {@link Person} object that
     * contains the encoded data.
     * 
     * @param bytes
     * @return the {@link Person} encoded in the ByteBuffer
     */
    public static Person fromByteBuffer(ByteBuffer bytes) {
        short fnameLength = bytes.getShort(); //read fnameBytes.length
        byte[] fnameBytes = new byte[fnameLength];
        bytes.get(fnameBytes); // read fnameBytes
        String fname = new String(fnameBytes, StandardCharsets.UTF_8); // convert fnameBytes -> fname

        short lnameLength = bytes.getShort(); // read lnameBytes.length;
        byte[] lnameBytes = new byte[lnameLength];
        bytes.get(lnameBytes); //read lameBytes
        String lname = new String(lnameBytes, StandardCharsets.UTF_8);

        byte age = bytes.get(); // read age

        boolean hasInternship = bytes.get() == 1 ? true: false; //read hasInternship

        List<String> friends = new ArrayList<>();
        while(bytes.hasRemaining()){
            short friendLength = bytes.getShort(); // read friendBytes.length
            byte[] friendBytes = new byte[friendLength];
            bytes.get(friendBytes); // read friendBytes
            String friend = new String(friendBytes, StandardCharsets.UTF_8);
            friends.add(friend);
        }
        Person person = new Person();
        person.hasInternship = hasInternship;
        person.age = age;
        person.friends = friends;
        person.fname = fname;
        person.lname = lname;
        return person;
    }

    /**
     * Return a {@link ByteBuffer} that contains all the information in this
     * class encoded in binary format.
     * 
     * @return a {@link ByteBuffer} for this object
     */
    public ByteBuffer toByteBuffer() {
        byte[] fnameBytes = fname.getBytes(StandardCharsets.UTF_8); //convert fname -> byte[]
        byte[] lnameBytes = lname.getBytes(StandardCharsets.UTF_8); //convert lname -> byte[]
        byte hasInternshipByte = (byte)(hasInternship ? 1 : 0); //convert boolean -> byte
        int capacity = fnameBytes.length + lnameBytes.length; // start summing capacity needed
        capacity += 1; //age
        capacity += 1; //hasInternshipByte
        List<byte[]> friendBytesList = new ArrayList<>(); // create a list to hold byte[] foreach friend
        for(String friend : friends){
            byte[] friendBytes = friend.getBytes(StandardCharsets.UTF_8); //convert each friend -> byte[]
            capacity += friendBytes.length; // increasing capacity to hold the friend
            capacity += 2; //friendBytes.length; increasing capacity to hold the length of the friend
            friendBytesList.add(friendBytes); //add the friend to a list for future reference
        }
        capacity += 2; // fnameBytes.length;
        capacity += 2; // lnameBytes.length
        ByteBuffer bytes = ByteBuffer.allocate(capacity);
        bytes.putShort((short) fnameBytes.length); // write fnameBytes.length
        bytes.put(fnameBytes); // write fnameBytes

        bytes.putShort((short) lnameBytes.length); // write lnameBytes.length
        bytes.put(lnameBytes); // write lnameBytes

        bytes.put(age); // write age

        bytes.put(hasInternshipByte); //write hasInternship (as a byte)

        for(byte[] friendBytes : friendBytesList){
            bytes.putShort((short) friendBytes.length); // write out the length of each friend
            bytes.put(friendBytes); // write out each friend
        }

        bytes.flip();
        return bytes;
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
