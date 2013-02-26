/*
 * File: BigIntegerFieldSerializer.java
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * The contents of this file are subject to the terms and conditions of 
 * the Common Development and Distribution License 1.0 (the "License").
 *
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the License by consulting the LICENSE.txt file
 * distributed with this file, or by consulting https://oss.oracle.com/licenses/CDDL
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

package com.oracle.coherence.common.serialization.fieldserializers;

import com.oracle.coherence.common.serialization.FieldSerializer;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * A {@link BigIntegerFieldSerializer} is a {@link FieldSerializer} for {@link BigInteger}s.
 * <p>
 * Copyright (c) 2011. All Rights Reserved. Oracle Corporation.<br>
 * Oracle is a registered trademark of Oracle Corporation and/or its affiliates.
 *
 * @author Charlie Helin
 */
public final class BigIntegerFieldSerializer implements FieldSerializer
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void readField(Object    object,
                          Field     field,
                          PofReader reader,
                          int       index) throws IllegalArgumentException, IllegalAccessException, IOException
    {
        field.set(object, reader.readBigInteger(index));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeField(Object    object,
                           Field     field,
                           PofWriter writer,
                           int       index) throws IllegalArgumentException, IOException, IllegalAccessException
    {
        writer.writeBigInteger(index, (BigInteger) field.get(object));
    }
}
