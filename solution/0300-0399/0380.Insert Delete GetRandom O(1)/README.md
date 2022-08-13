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

哈希表存放每个元素的值和对应的下标，而动态列表在每个下标位置存放每个元素。由动态列表实现元素的随机返回。

注意，在 `remove()` 实现上，将列表的最后一个元素设置到待删元素的位置上，然后删除最后一个元素，这样在删除元素的时候，不需要挪动一大批元素，从而实现 `O(1)` 时间内操作。

操作细节：

1. **插入**

    每次添加新数值时，先使用哈希表判断该数值是否存在，存在则直接返回 false。不存在则进行插入操作，只要将该数值添加到数组尾部即可，并将该数值和其下标的映射存入哈希表。

2. **删除**

    删除同样需使用哈希表判断是否存在，若不存在则返回 false。存在则进行删除操作，在哈希表中删除时间复杂度为 O(1)，但是在数值中删除比较麻烦。若只是直接删除，则为了保证数组内存连续性需将删除数值后面的数值均前移一位，时间复杂度为 O(n)。比较好的处理方式是，用数组的最后一个数值去填充需要删除的数值的内存，其他数值在数组中的位置保持不变，并将这个拿来填充的数值的下标更新即可，最后只要删除数组最后一个数值，同样可以保证时间复杂度为 O(1)。

3. **随机返回**

    只要随机生成数组下标范围内一个随机下标值，返回该数组下标内的数值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class RandomizedSet:
    def __init__(self):
        self.m = {}
        self.l = []

    def insert(self, val: int) -> bool:
        if val in self.m:
            return False
        self.m[val] = len(self.l)
        self.l.append(val)
        return True

    def remove(self, val: int) -> bool:
        if val not in self.m:
            return False
        idx = self.m[val]
        self.l[idx] = self.l[-1]
        self.m[self.l[-1]] = idx
        self.l.pop()
        self.m.pop(val)
        return True

    def getRandom(self) -> int:
        return choice(self.l)


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
    private Map<Integer, Integer> m = new HashMap<>();
    private List<Integer> l = new ArrayList<>();
    private Random rnd = new Random();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (m.containsKey(val)) {
            return false;
        }
        m.put(val, l.size());
        l.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        int idx = m.get(val);
        l.set(idx, l.get(l.size() - 1));
        m.put(l.get(l.size() - 1), idx);
        l.remove(l.size() - 1);
        m.remove(val);
        return true;
    }

    public int getRandom() {
        int idx = rnd.nextInt(l.size());
        return l.get(idx);
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
private:
    unordered_map<int, int> mp;
    vector<int> nums;

public:
    RandomizedSet() {
    }

    bool insert(int val) {
        if (mp.count(val)) return false;
        mp[val] = nums.size();
        nums.push_back(val);
        return true;
    }

    bool remove(int val) {
        if (!mp.count(val)) return false;
        int idx = mp[val];
        nums[idx] = nums.back();
        mp[nums.back()] = idx;
        mp.erase(val);
        nums.pop_back();
        return true;
    }

    int getRandom() {
        return nums[rand() % nums.size()];
    }
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
	m map[int]int
	l []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{map[int]int{}, []int{}}
}

func (this *RandomizedSet) Insert(val int) bool {
	if _, ok := this.m[val]; ok {
		return false
	}
	this.m[val] = len(this.l)
	this.l = append(this.l, val)
	return true
}

func (this *RandomizedSet) Remove(val int) bool {
	if _, ok := this.m[val]; !ok {
		return false
	}
	idx := this.m[val]
	this.l[idx] = this.l[len(this.l)-1]
	this.m[this.l[len(this.l)-1]] = idx
	this.l = this.l[:len(this.l)-1]
	delete(this.m, val)
	return true
}

func (this *RandomizedSet) GetRandom() int {
	return this.l[rand.Intn(len(this.l))]
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
    public map: Map<number, number>;
    public arr: number[];
    public index: number;

    constructor() {
        this.map = new Map();
        this.arr = new Array(2 * 10 ** 5).fill(0);
        this.index = -1;
    }

    insert(val: number): boolean {
        const { map, arr } = this;
        if (map.has(val)) {
            return false;
        }
        this.index++;
        arr[this.index] = val;
        map.set(val, this.index);
        return true;
    }

    remove(val: number): boolean {
        const { arr, map, index } = this;
        if (!map.has(val)) {
            return false;
        }
        const i = map.get(val);
        [arr[i], arr[index]] = [arr[index], arr[i]];
        map.set(arr[i], i);
        map.delete(arr[index]);
        this.index--;
        return true;
    }

    getRandom(): number {
        const i = Math.floor(Math.random() * (this.index + 1));
        return this.arr[i];
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

### **...**

```

```

<!-- tabs:end -->
