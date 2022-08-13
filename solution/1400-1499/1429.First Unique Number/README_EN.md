# [1429. First Unique Number](https://leetcode.com/problems/first-unique-number)

[中文文档](/solution/1400-1499/1429.First%20Unique%20Number/README.md)

## Description

<p>You have a queue of integers, you need to retrieve the first unique integer in the queue.</p>

<p>Implement the <code>FirstUnique</code>&nbsp;class:</p>

<ul>
	<li><code>FirstUnique(int[] nums)</code> Initializes the object with the numbers in the queue.</li>
	<li><code>int showFirstUnique()</code>&nbsp;returns the value of <strong>the&nbsp;first unique</strong> integer of the queue, and returns <strong>-1</strong> if there is no such integer.</li>
	<li><code>void add(int value)</code>&nbsp;insert value&nbsp;to&nbsp;the queue.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<b>Input: </b>
[&quot;FirstUnique&quot;,&quot;showFirstUnique&quot;,&quot;add&quot;,&quot;showFirstUnique&quot;,&quot;add&quot;,&quot;showFirstUnique&quot;,&quot;add&quot;,&quot;showFirstUnique&quot;]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
<b>Output: </b>
[null,2,null,2,null,3,null,-1]
<b>Explanation: </b>
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<b>Input: </b>
[&quot;FirstUnique&quot;,&quot;showFirstUnique&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;add&quot;,&quot;showFirstUnique&quot;]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
<b>Output: </b>
[null,-1,null,null,null,null,null,17]
<b>Explanation: </b>
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<b>Input: </b>
[&quot;FirstUnique&quot;,&quot;showFirstUnique&quot;,&quot;add&quot;,&quot;showFirstUnique&quot;]
[[[809]],[],[809],[]]
<b>Output: </b>
[null,809,null,-1]
<b>Explanation: </b>
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^8</code></li>
	<li><code>1 &lt;= value &lt;= 10^8</code></li>
	<li>At most <code>50000</code>&nbsp;calls will be made to <code>showFirstUnique</code>&nbsp;and <code>add</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class FirstUnique:
    def __init__(self, nums: List[int]):
        self.counter = OrderedDict()
        self.unique_nums = OrderedDict()
        for num in nums:
            self.counter[num] = self.counter.get(num, 0) + 1
        for k, v in self.counter.items():
            if v == 1:
                self.unique_nums[k] = 1

    def showFirstUnique(self) -> int:
        if len(self.unique_nums) == 0:
            return -1
        for k in self.unique_nums.keys():
            return k

    def add(self, value: int) -> None:
        if value not in self.counter:
            self.counter[value] = 1
            self.unique_nums[value] = 1
        else:
            self.counter[value] += 1
            if value in self.unique_nums:
                self.unique_nums.pop(value)


# Your FirstUnique object will be instantiated and called as such:
# obj = FirstUnique(nums)
# param_1 = obj.showFirstUnique()
# obj.add(value)
```

### **Java**

```java
class FirstUnique {
    private Map<Integer, Integer> counter;
    private Set<Integer> uniqueNums;

    public FirstUnique(int[] nums) {
        counter = new LinkedHashMap<>();
        uniqueNums = new LinkedHashSet<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueNums.add(entry.getKey());
            }
        }
    }

    public int showFirstUnique() {
        return uniqueNums.isEmpty() ? -1 : uniqueNums.iterator().next();
    }

    public void add(int value) {
        if (!counter.containsKey(value)) {
            counter.put(value, 1);
            uniqueNums.add(value);
        } else {
            counter.put(value, counter.get(value) + 1);
            uniqueNums.remove(value);
        }
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
```

### **...**

```

```

<!-- tabs:end -->
