/*
 * Flatworm - A Java Flat File Importer/Exporter Copyright (C) 2004 James M. Turner.
 * Extended by James Lawrence 2005
 * Extended by Josh Brackett in 2011 and 2012
 * Extended by Alan Henson in 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.blackbear.flatworm.config.impl;

import com.blackbear.flatworm.errors.FlatwormConfigurationException;
import com.blackbear.flatworm.errors.FlatwormParserException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test the ScriptIdentityImpl class's capabilities around script parsing and line identification.
 *
 * @author Alan Henson
 */
public class ScriptIdentityTest extends AbstractBaseIdentityTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void simpleJavaScriptFunction() {
        try {
            String script = String.format(
                    "function %s(fileFormat, line) { " +
                    "   return fileFormat.getLineNumber() == 0; " +
                    "}",
                    ScriptIdentityImpl.DEFAULT_SCRIPT_IDENTITY_FUNCTION_NAME);
            ScriptIdentityImpl scriptIdentity = new ScriptIdentityImpl();
            scriptIdentity.setScript(script);
            String line = "";
            assertTrue(scriptIdentity.matchesIdentity(record, fileFormat, line));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void invalidScript() throws FlatwormParserException, FlatwormConfigurationException {
        thrown.expect(FlatwormParserException.class);
        String script = "";
        ScriptIdentityImpl scriptIdentity = new ScriptIdentityImpl();
        scriptIdentity.setScript(script);

        String line = "";
        scriptIdentity.matchesIdentity(record, fileFormat, line);
    }
}
