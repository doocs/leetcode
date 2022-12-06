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
<p><strong class="example">Example 1:</strong></p>

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

<p><strong class="example">Example 2:</strong></p>

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

<p><strong class="example">Example 3:</strong></p>

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
        self.cnt = Counter(nums)
        self.unique = OrderedDict({v: 1 for v in nums if self.cnt[v] == 1})

    def showFirstUnique(self) -> int:
        return -1 if not self.unique else next(v for v in self.unique.keys())

    def add(self, value: int) -> None:
        self.cnt[value] += 1
        if self.cnt[value] == 1:
            self.unique[value] = 1
        elif value in self.unique:
            self.unique.pop(value)

# Your FirstUnique object will be instantiated and called as such:
# obj = FirstUnique(nums)
# param_1 = obj.showFirstUnique()
# obj.add(value)
```

```python
class FirstUnique:

    def __init__(self, nums: List[int]):
        self.cnt = Counter(nums)
        self.q = deque(nums)

    def showFirstUnique(self) -> int:
        while self.q and self.cnt[self.q[0]] != 1:
            self.q.popleft()
        return -1 if not self.q else self.q[0]

    def add(self, value: int) -> None:
        self.cnt[value] += 1
        self.q.append(value)


# Your FirstUnique object will be instantiated and called as such:
# obj = FirstUnique(nums)
# param_1 = obj.showFirstUnique()
# obj.add(value)
```

### **Java**

```java
class FirstUnique {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Set<Integer> unique = new LinkedHashSet<>();

    public FirstUnique(int[] nums) {
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        for (int v : nums) {
            if (cnt.get(v) == 1) {
                unique.add(v);
            }
        }
    }

    public int showFirstUnique() {
        return unique.isEmpty() ? -1 : unique.iterator().next();
    }

    public void add(int value) {
        cnt.put(value, cnt.getOrDefault(value, 0) + 1);
        if (cnt.get(value) == 1) {
            unique.add(value);
        } else {
            unique.remove(value);
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

```java
class FirstUnique {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Deque<Integer> q = new ArrayDeque<>();

    public FirstUnique(int[] nums) {
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            q.offer(v);
        }
    }

    public int showFirstUnique() {
        while (!q.isEmpty() && cnt.get(q.peekFirst()) != 1) {
            q.poll();
        }
        return q.isEmpty() ? -1 : q.peekFirst();
    }

    public void add(int value) {
        cnt.put(value, cnt.getOrDefault(value, 0) + 1);
        q.offer(value);
    }
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
```

### **C++**

```cpp
class FirstUnique {
public:
    FirstUnique(vector<int>& nums) {
        for (int& v : nums) {
            ++cnt[v];
            q.push_back(v);
        }
    }

    int showFirstUnique() {
        while (q.size() && cnt[q.front()] != 1) q.pop_front();
        return q.size() ? q.front() : -1;
    }

    void add(int value) {
        ++cnt[value];
        q.push_back(value);
    }

private:
    unordered_map<int, int> cnt;
    deque<int> q;
};

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique* obj = new FirstUnique(nums);
 * int param_1 = obj->showFirstUnique();
 * obj->add(value);
 */
```

### **Go**

```go
type FirstUnique struct {
	cnt map[int]int
	q   []int
}

func Constructor(nums []int) FirstUnique {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	return FirstUnique{cnt, nums}
}

func (this *FirstUnique) ShowFirstUnique() int {
	for len(this.q) > 0 && this.cnt[this.q[0]] != 1 {
		this.q = this.q[1:]
	}
	if len(this.q) > 0 {
		return this.q[0]
	}
	return -1
}

func (this *FirstUnique) Add(value int) {
	this.cnt[value]++
	this.q = append(this.q, value)
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.ShowFirstUnique();
 * obj.Add(value);
 */
```

### **...**

```

```

<!-- tabs:end -->
