/* MIT License
 *
 * Copyright (c) 2019 Ivo Korinek
 * Copyright (c) 2019 Gymnazium Nad Aleji
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cz.alisma.alej.text.templater;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TemplateFillerTest {
     @Parameters
     public static Collection<Object[]> data() {
         return Arrays.asList(new Object[][] {
            {
                "Well hello there, {{ mister }}",
                "mister",
                "James Bond",
                "Well hello there, James Bond"
            }, {
                "{{ time }} no see!",
                "time",
                "Long time",
                "Long time no see!"
            }, {
                "Look at that seemingly supernatural {{ skill }} of his!",
                "skill",
                "cooking",
                "Look at that seemingly supernatural cooking of his!"
            }, {
                "It will be ${{ price }}, please",
                "price",
                "7.95",
                "It will be $7.95, please"
            }, {
                "{{ empty }}",
                "empty",
                "Hewwo? OwO",
                "Hewwo? OwO"
            }, {
                "{{ empty }}",
                "empty",
                "",
                ""
            }
         });
     }

     private List<String> input = new ArrayList<String>();
     private Map<String, String> keys = new HashMap<String, String>();
     private String expected;

     public TemplateFillerTest(
        String input,
        String key,
        String value,
        String expected
    ) {
         this.input.add(input);
         this.keys.put(key, value);
         this.expected = expected;
     }

     @Test
     public void test() {
         assertEquals(expected + "\n", TemplateFiller.fill(input, keys));
     }
 }
