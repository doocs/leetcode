# [692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words)

[中文文档](/solution/0600-0699/0692.Top%20K%20Frequent%20Words/README.md)

## Description

<p>Given a non-empty list of words, return the <i>k</i> most frequent elements.</p>

<p>Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> ["i", "love", "leetcode", "i", "love", "coding"], k = 2

<b>Output:</b> ["i", "love"]

<b>Explanation:</b> "i" and "love" are the two most frequent words.

    Note that "i" comes before "love" due to a lower alphabetical order.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4

<b>Output:</b> ["the", "is", "sunny", "day"]

<b>Explanation:</b> "the", "is", "sunny" and "day" are the four most frequent words,

    with the number of occurrence being 4, 3, 2 and 1 respectively.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>You may assume <i>k</i> is always valid, 1 &le; <i>k</i> &le; number of unique elements.</li>

<li>Input words contain only lowercase letters.</li>

</ol>

</p>

<p><b>Follow up:</b><br />

<ol>

<li>Try to solve it in <i>O</i>(<i>n</i> log <i>k</i>) time and <i>O</i>(<i>n</i>) extra space.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        counter = collections.Counter(words)
        res = sorted(counter, key=lambda word: (-counter[word], word))
        return res[:k]
```

### **Java**

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            if (counter.get(a).equals(counter.get(b))) {
                return b.compareTo(a);
            }
            return counter.get(a) - counter.get(b);
        });
        for (String word : counter.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }
        Collections.reverse(res);
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
