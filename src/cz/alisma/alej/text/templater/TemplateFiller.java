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

import java.util.Scanner;
import java.util.Map;

public class TemplateFiller {
    public static String fill(Scanner sc, Map<String, String> templates) {
        StringBuilder result = new StringBuilder();
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if(nextLine.isBlank()) {
                result.append("\n");
                continue;
            }
            Scanner lineScanner = new Scanner(nextLine);
            boolean first = true;
            while (lineScanner.hasNext()) {
                String next = lineScanner.next();
                if(!first) {
                    result.append(" ");
                } else {
                    first = false;
                }
                if (!next.contains("{{")) {
                result.append(next);
                } else {
                    String key = lineScanner.next();
                    result.append(templates.get(key));
                    next = lineScanner.next();
                }
            }
            lineScanner.close();
            result.append("\n");
        }
        return result.toString();
    }
}