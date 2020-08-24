# [642. Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system)

[中文文档](/solution/0600-0699/0642.Design%20Search%20Autocomplete%20System/README.md)

## Description
<p>Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character <code>'#'</code>). For <b>each character</b> they type <b>except '#'</b>, you need to return the <b>top 3</b> historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:</p>

<ol>
	<li>The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.</li>
	<li>The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).</li>
	<li>If less than 3 hot sentences exist, then just return as many as you can.</li>
	<li>When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.</li>
</ol>

<p>Your job is to implement the following functions:</p>

<p>The constructor function:</p>

<p><code>AutocompleteSystem(String[] sentences, int[] times):</code> This is the constructor. The input is <b>historical data</b>. <code>Sentences</code> is a string array consists of previously typed sentences. <code>Times</code> is the corresponding times a sentence has been typed. Your system should record these historical data.</p>

<p>Now, the user wants to input a new sentence. The following function will provide the next character the user types:</p>

<p><code>List<String> input(char c):</code> The input <code>c</code> is the next character typed by the user. The character will only be lower-case letters (<code>'a'</code> to <code>'z'</code>), blank space (<code>' '</code>) or a special character (<code>'#'</code>). Also, the previously typed sentence should be recorded in your system. The output will be the <b>top 3</b> historical hot sentences that have prefix the same as the part of sentence already typed.</p>
 

<p><b>Example:</b><br />
<b>Operation:</b> AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])<br />
The system have already tracked down the following sentences and their corresponding times:<br />
<code>"i love you"</code> : <code>5</code> times<br />
<code>"island"</code> : <code>3</code> times<br />
<code>"ironman"</code> : <code>2</code> times<br />
<code>"i love leetcode"</code> : <code>2</code> times<br />
Now, the user begins another search:<br />
<br />
<b>Operation:</b> input('i')<br />
<b>Output:</b> ["i love you", "island","i love leetcode"]<br />
<b>Explanation:</b><br />
There are four sentences that have prefix <code>"i"</code>. Among them, "ironman" and "i love leetcode" have same hot degree. Since <code>' '</code> has ASCII code 32 and <code>'r'</code> has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.<br />
<br />
<b>Operation:</b> input(' ')<br />
<b>Output:</b> ["i love you","i love leetcode"]<br />
<b>Explanation:</b><br />
There are only two sentences that have prefix <code>"i "</code>.<br />
<br />
<b>Operation:</b> input('a')<br />
<b>Output:</b> []<br />
<b>Explanation:</b><br />
There are no sentences that have prefix <code>"i a"</code>.<br />
<br />
<b>Operation:</b> input('#')<br />
<b>Output:</b> []<br />
<b>Explanation:</b><br />
The user finished the input, the sentence <code>"i a"</code> should be saved as a historical sentence in system. And the following input will be counted as a new search.</p>
 

<p><b>Note:</b></p>

<ol>
	<li>The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.</li>
	<li>The number of <b>complete sentences</b> that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.</li>
	<li>Please use double-quote instead of single-quote when you write test cases even for a character input.</li>
	<li>Please remember to <b>RESET</b> your class variables declared in class AutocompleteSystem, as static/class variables are <b>persisted across multiple test cases</b>. Please see <a href="https://leetcode.com/faq/#different-output">here</a> for more details.</li>
</ol>

<p> </p>



## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->