# [380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1)

[中文文档](/solution/0300-0399/0380.Insert%20Delete%20GetRandom%20O%281%29/README.md)

## Description

<p>Implement the <code>RandomizedSet</code> class:</p>

<ul>
	<li><code>RandomizedSet()</code> Initializes the <code>RandomizedSet</code> object.</li>
	<li><code>bool insert(int val)</code> Inserts an item <code>val</code> into the set if not present. Returns <code>true</code> if the item was not present, <code>false</code> otherwise.</li>
	<li><code>bool remove(int val)</code> Removes an item <code>val</code> from the set if present. Returns <code>true</code> if the item was present, <code>false</code> otherwise.</li>
	<li><code>int getRandom()</code> Returns a random element from the current set of elements (it&#39;s guaranteed that at least one element exists when this method is called). Each element must have the <b>same probability</b> of being returned.</li>
</ul>

<p>You must implement the functions of the class such that each function works in&nbsp;<strong>average</strong>&nbsp;<code>O(1)</code>&nbsp;time complexity.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;RandomizedSet&quot;, &quot;insert&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;]
[[], [1], [2], [2], [], [1], [2], []]
<strong>Output</strong>
[null, true, false, true, 2, true, false, 2]

<strong>Explanation</strong>
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
	<li>At most <code>2 *&nbsp;</code><code>10<sup>5</sup></code> calls will be made to <code>insert</code>, <code>remove</code>, and <code>getRandom</code>.</li>
	<li>There will be <strong>at least one</strong> element in the data structure when <code>getRandom</code> is called.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
