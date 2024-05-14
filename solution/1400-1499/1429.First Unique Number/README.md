# [1429. 第一个唯一数字 🔒](https://leetcode.cn/problems/first-unique-number)

[English Version](/solution/1400-1499/1429.First%20Unique%20Number/README_EN.md)

<!-- tags:设计,队列,数组,哈希表,数据流 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一系列整数，插入一个队列中，找出队列中第一个唯一整数。</p>

<p>实现 <code>FirstUnique</code> 类：</p>

<ul>
	<li><code>FirstUnique(int[] nums)</code> 用数组里的数字初始化队列。</li>
	<li><code>int showFirstUnique()</code> 返回队列中的<strong> 第一个唯一 </strong>整数的值。如果没有唯一整数，返回 -1。（译者注：此方法不移除队列中的任何元素）</li>
	<li><code>void add(int value)</code> 将 value 插入队列中。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
<strong>输出：</strong>
[null,2,null,2,null,3,null,-1]
<strong>解释：</strong>
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // 返回 2
firstUnique.add(5);            // 此时队列为 [2,3,5,5]
firstUnique.showFirstUnique(); // 返回 2
firstUnique.add(2);            // 此时队列为 [2,3,5,5,2]
firstUnique.showFirstUnique(); // 返回 3
firstUnique.add(3);            // 此时队列为 [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // 返回 -1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
<strong>输出：</strong>
[null,-1,null,null,null,null,null,17]
<strong>解释：</strong>
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // 返回 -1
firstUnique.add(7);            // 此时队列为 [7,7,7,7,7,7,7]
firstUnique.add(3);            // 此时队列为 [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // 此时队列为 [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // 此时队列为 [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // 此时队列为 [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // 返回 17
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
<strong>输出：</strong>
[null,809,null,-1]
<strong>解释：</strong>
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // 返回 809
firstUnique.add(809);          // 此时队列为 [809,809]
firstUnique.showFirstUnique(); // 返回 -1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10^5</code></li>
	<li><code>1 <= nums[i] <= 10^8</code></li>
	<li><code>1 <= value <= 10^8</code></li>
	<li>最多调用 <code>5000</code> 次 <code>showFirstUnique</code> 和 <code>add</code> 。</li>
</ul>

## 解法

### 方法一：哈希表 + 双端队列

我们可以使用哈希表 $cnt$ 统计每个数字出现的次数，使用双端队列 $q$ 按顺序维护出现的数字。

调用 `showFirstUnique` 方法时，判断队列 $q$ 的队头元素是否在哈希表 $cnt$ 中出现的次数是否为 $1$，如果是，则返回队头元素，否则将队头元素弹出，直到队列为空或者队头元素在哈希表 $cnt$ 中出现的次数为 $1$，如果队列为空，则返回 $-1$。

调用 `add` 方法时，将数字加入哈希表 $cnt$ 中，并将数字加入队列 $q$ 中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

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

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
