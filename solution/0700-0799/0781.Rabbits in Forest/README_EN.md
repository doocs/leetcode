# [781. Rabbits in Forest](https://leetcode.com/problems/rabbits-in-forest)

[中文文档](/solution/0700-0799/0781.Rabbits%20in%20Forest/README.md)

## Description

<p>In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them. Those <code>answers</code> are placed in an array.</p>



<p>Return the minimum number of rabbits that could be in the forest.</p>



<pre>

<strong>Examples:</strong>

<strong>Input:</strong> answers = [1, 1, 2]

<strong>Output:</strong> 5

<strong>Explanation:</strong>

The two rabbits that answered &quot;1&quot; could both be the same color, say red.

The rabbit than answered &quot;2&quot; can&#39;t be red or the answers would be inconsistent.

Say the rabbit that answered &quot;2&quot; was blue.

Then there should be 2 other blue rabbits in the forest that didn&#39;t answer into the array.

The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn&#39;t.



<strong>Input:</strong> answers = [10, 10, 10]

<strong>Output:</strong> 11



<strong>Input:</strong> answers = []

<strong>Output:</strong> 0

</pre>



<p><strong>Note:</strong></p>



<ol>
	<li><code>answers</code> will have length at most <code>1000</code>.</li>
	<li>Each <code>answers[i]</code> will be an integer in the range <code>[0, 999]</code>.</li>
</ol>



## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        counter = collections.Counter(answers)
        return sum([math.ceil(v / (k + 1)) * (k + 1) for k, v in counter.items()])
```

### **Java**

```java
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int e : answers) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int answer = entry.getKey(), count = entry.getValue();
            res += (int) Math.ceil(count / ((answer + 1) * 1.0)) * (answer + 1);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
