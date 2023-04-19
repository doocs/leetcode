# [380. O(1) 时间插入、删除和获取随机元素](https://leetcode.cn/problems/insert-delete-getrandom-o1)

[English Version](/solution/0300-0399/0380.Insert%20Delete%20GetRandom%20O%281%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>实现<code>RandomizedSet</code> 类：</p>

<div class="original__bRMd">
<div>
<ul>
	<li><code>RandomizedSet()</code> 初始化 <code>RandomizedSet</code> 对象</li>
	<li><code>bool insert(int val)</code> 当元素 <code>val</code> 不存在时，向集合中插入该项，并返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
	<li><code>bool remove(int val)</code> 当元素 <code>val</code> 存在时，从集合中移除该项，并返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
	<li><code>int getRandom()</code> 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 <strong>相同的概率</strong> 被返回。</li>
</ul>

<p>你必须实现类的所有函数，并满足每个函数的 <strong>平均</strong> 时间复杂度为 <code>O(1)</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
<strong>输出</strong>
[null, true, false, true, 2, true, false, 2]

<strong>解释</strong>
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
	<li>最多调用 <code>insert</code>、<code>remove</code> 和 <code>getRandom</code> 函数 <code>2 *&nbsp;</code><code>10<sup>5</sup></code> 次</li>
	<li>在调用 <code>getRandom</code> 方法时，数据结构中 <strong>至少存在一个</strong> 元素。</li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 动态列表**

我们定义一个动态列表 $q$，用于存储集合中的元素，定义一个哈希表 $d$，用于存储每个元素在 $q$ 中的下标。

插入元素时，如果元素已经存在于哈希表 $d$ 中，直接返回 `false`；否则，我们将元素插入到动态列表 $q$ 的末尾，同时将元素和其在 $q$ 中的下标插入到哈希表 $d$ 中，最后返回 `true`。

删除元素时，如果元素不存在于哈希表 $d$ 中，直接返回 `false`；否则，我们从哈希表中获取元素在列表 $q$ 中的下标 $i$，然后将列表 $q$ 的最后一个元素 $q[-1]$ 与 $q[i]$ 交换，然后将哈希表中 $q[-1]$ 的下标更新为 $i$，最后将 $q$ 的最后一个元素删除，同时将元素从哈希表中删除，最后返回 `true`。

获取随机元素时，我们从动态列表 $q$ 中随机选择一个元素返回即可。

时间复杂度 $O(1)$，空间复杂度 $O(n)$。其中 $n$ 为集合中元素的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class RandomizedSet:

    def __init__(self):
        self.d = {}
        self.q = []

    def insert(self, val: int) -> bool:
        if val in self.d:
            return False
        self.d[val] = len(self.q)
        self.q.append(val)
        return True

    def remove(self, val: int) -> bool:
        if val not in self.d:
            return False
        i = self.d[val]
        self.d[self.q[-1]] = i
        self.q[i] = self.q[-1]
        self.q.pop()
        self.d.pop(val)
        return True

    def getRandom(self) -> int:
        return choice(self.q)


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class RandomizedSet {
    private Map<Integer, Integer> d = new HashMap<>();
    private List<Integer> q = new ArrayList<>();
    private Random rnd = new Random();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (d.containsKey(val)) {
            return false;
        }
        d.put(val, q.size());
        q.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!d.containsKey(val)) {
            return false;
        }
        int i = d.get(val);
        d.put(q.get(q.size() - 1), i);
        q.set(i, q.get(q.size() - 1));
        q.remove(q.size() - 1);
        d.remove(val);
        return true;
    }

    public int getRandom() {
        return q.get(rnd.nextInt(q.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

### **C++**

```cpp
class RandomizedSet {
public:
    RandomizedSet() {

    }

    bool insert(int val) {
        if (d.count(val)) {
            return false;
        }
        d[val] = q.size();
        q.push_back(val);
        return true;
    }

    bool remove(int val) {
        if (!d.count(val)) {
            return false;
        }
        int i = d[val];
        d[q.back()] = i;
        q[i] = q.back();
        q.pop_back();
        d.erase(val);
        return true;
    }

    int getRandom() {
        return q[rand() % q.size()];
    }

private:
    unordered_map<int, int> d;
    vector<int> q;
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */
```

### **Go**

```go
type RandomizedSet struct {
	d map[int]int
	q []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{map[int]int{}, []int{}}
}

func (this *RandomizedSet) Insert(val int) bool {
	if _, ok := this.d[val]; ok {
		return false
	}
	this.d[val] = len(this.q)
	this.q = append(this.q, val)
	return true
}

func (this *RandomizedSet) Remove(val int) bool {
	if _, ok := this.d[val]; !ok {
		return false
	}
	i := this.d[val]
	this.d[this.q[len(this.q)-1]] = i
	this.q[i] = this.q[len(this.q)-1]
	this.q = this.q[:len(this.q)-1]
	delete(this.d, val)
	return true
}

func (this *RandomizedSet) GetRandom() int {
	return this.q[rand.Intn(len(this.q))]
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */
```

### **TypeScript**

```ts
class RandomizedSet {
    private d: Map<number, number> = new Map();
    private q: number[] = [];

    constructor() {}

    insert(val: number): boolean {
        if (this.d.has(val)) {
            return false;
        }
        this.d.set(val, this.q.length);
        this.q.push(val);
        return true;
    }

    remove(val: number): boolean {
        if (!this.d.has(val)) {
            return false;
        }
        const i = this.d.get(val)!;
        this.d.set(this.q[this.q.length - 1], i);
        this.q[i] = this.q[this.q.length - 1];
        this.q.pop();
        this.d.delete(val);
        return true;
    }

    getRandom(): number {
        return this.q[Math.floor(Math.random() * this.q.length)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = new RandomizedSet()
 * var param_1 = obj.insert(val)
 * var param_2 = obj.remove(val)
 * var param_3 = obj.getRandom()
 */
```

### **Rust**

```rust
use std::collections::HashSet;
use rand::Rng;

struct RandomizedSet {
    list: HashSet<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl RandomizedSet {

    fn new() -> Self {
        Self {
            list: HashSet::new()
        }
    }

    fn insert(&mut self, val: i32) -> bool {
        self.list.insert(val)
    }

    fn remove(&mut self, val: i32) -> bool {
        self.list.remove(&val)
    }

    fn get_random(&self) -> i32 {
        let i = rand::thread_rng().gen_range(0, self.list.len());
        *self.list.iter().collect::<Vec<&i32>>()[i]
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * let obj = RandomizedSet::new();
 * let ret_1: bool = obj.insert(val);
 * let ret_2: bool = obj.remove(val);
 * let ret_3: i32 = obj.get_random();
 */
```

### **C#**

```cs
public class RandomizedSet {
    private Dictionary<int, int> d = new Dictionary<int, int>();
    private List<int> q = new List<int>();

    public RandomizedSet() {

    }

    public bool Insert(int val) {
        if (d.ContainsKey(val)) {
            return false;
        }
        d.Add(val, q.Count);
        q.Add(val);
        return true;
    }

    public bool Remove(int val) {
        if (!d.ContainsKey(val)) {
            return false;
        }
        int i = d[val];
        d[q[q.Count - 1]] = i;
        q[i] = q[q.Count - 1];
        q.RemoveAt(q.Count - 1);
        d.Remove(val);
        return true;
    }

    public int GetRandom() {
        return q[new Random().Next(0, q.Count)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * bool param_1 = obj.Insert(val);
 * bool param_2 = obj.Remove(val);
 * int param_3 = obj.GetRandom();
 */
```

### **...**

```

```

<!-- tabs:end -->
