# [1865. Finding Pairs With a Certain Sum](https://leetcode.com/problems/finding-pairs-with-a-certain-sum)

[中文文档](/solution/1800-1899/1865.Finding%20Pairs%20With%20a%20Certain%20Sum/README.md)

## Description

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>. You are tasked to implement a data structure that supports queries of two types:</p>

<ol>
	<li><strong>Add</strong> a positive integer to an element of a given index in the array <code>nums2</code>.</li>
	<li><strong>Count</strong> the number of pairs <code>(i, j)</code> such that <code>nums1[i] + nums2[j]</code> equals a given value (<code>0 &lt;= i &lt; nums1.length</code> and <code>0 &lt;= j &lt; nums2.length</code>).</li>
</ol>

<p>Implement the <code>FindSumPairs</code> class:</p>

<ul>
	<li><code>FindSumPairs(int[] nums1, int[] nums2)</code> Initializes the <code>FindSumPairs</code> object with two integer arrays <code>nums1</code> and <code>nums2</code>.</li>
	<li><code>void add(int index, int val)</code> Adds <code>val</code> to <code>nums2[index]</code>, i.e., apply <code>nums2[index] += val</code>.</li>
	<li><code>int count(int tot)</code> Returns the number of pairs <code>(i, j)</code> such that <code>nums1[i] + nums2[j] == tot</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FindSumPairs&quot;, &quot;count&quot;, &quot;add&quot;, &quot;count&quot;, &quot;count&quot;, &quot;add&quot;, &quot;add&quot;, &quot;count&quot;]
[[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
<strong>Output</strong>
[null, 8, null, 2, 1, null, null, 11]

<strong>Explanation</strong>
FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
findSumPairs.count(7);  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
findSumPairs.add(3, 2); // now nums2 = [1,4,5,<strong><u>4</u></strong><code>,5,4</code>]
findSumPairs.count(8);  // return 2; pairs (5,2), (5,4) make 3 + 5
findSumPairs.count(4);  // return 1; pair (5,0) makes 3 + 1
findSumPairs.add(0, 1); // now nums2 = [<strong><u><code>2</code></u></strong>,4,5,4<code>,5,4</code>]
findSumPairs.add(1, 1); // now nums2 = [<code>2</code>,<strong><u>5</u></strong>,5,4<code>,5,4</code>]
findSumPairs.count(7);  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= index &lt; nums2.length</code></li>
	<li><code>1 &lt;= val &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= tot &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>1000</code> calls are made to <code>add</code> and <code>count</code> <strong>each</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class FindSumPairs:
    def __init__(self, nums1: List[int], nums2: List[int]):
        self.nums1 = nums1
        self.nums2 = nums2
        self.counter = Counter(nums2)

    def add(self, index: int, val: int) -> None:
        old_val = self.nums2[index]
        self.counter[old_val] -= 1
        self.nums2[index] += val
        self.counter[old_val + val] += 1

    def count(self, tot: int) -> int:
        return sum([self.counter[tot - num] for num in self.nums1])


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)
```

### **Java**

```java
class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> counter;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        counter = new HashMap<>();
        for (int num : nums2) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int oldVal = nums2[index];
        counter.put(oldVal, counter.get(oldVal) - 1);
        nums2[index] += val;
        counter.put(oldVal + val, counter.getOrDefault(oldVal + val, 0) + 1);
    }

    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            res += counter.getOrDefault(tot - num, 0);
        }
        return res;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
```

### **...**

```

```

<!-- tabs:end -->
