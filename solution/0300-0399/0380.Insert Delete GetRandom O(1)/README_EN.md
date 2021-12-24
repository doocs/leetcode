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
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>insert</code>, <code>remove</code>, and <code>getRandom</code>.</li>
	<li>There will be <strong>at least one</strong> element in the data structure when <code>getRandom</code> is called.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you implement the functions of the class with each function works in <strong>average</strong> <code>O(1)</code> time?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class RandomizedSet:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.m = {}
        self.l = []

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        if val in self.m:
            return False
        self.m[val] = len(self.l)
        self.l.append(val)
        return True

    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val not in self.m:
            return False
        idx = self.m[val]
        last_idx = len(self.l) - 1
        self.m[self.l[last_idx]] = idx
        self.m.pop(val)
        self.l[idx] = self.l[last_idx]
        self.l.pop()
        return True

    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return random.choice(self.l)


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
```

### **Java**

```java
class RandomizedSet {
    private Map<Integer, Integer> m;
    private List<Integer> l;
    private Random rnd;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        m = new HashMap<>();
        l = new ArrayList<>();
        rnd = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (m.containsKey(val)) {
            return false;
        }
        m.put(val, l.size());
        l.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        int idx = m.get(val);
        int lastIdx = l.size() - 1;
        m.put(l.get(lastIdx), idx);
        m.remove(val);
        l.set(idx, l.get(lastIdx));
        l.remove(lastIdx);
        return true;
    }

    /** Get a random element from the set. */
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
    unordered_map<int, int> mp;
    vector<int> nums;
public:
    RandomizedSet() {

    }

    bool insert(int val) {
        if (mp.count(val))
            return false;

        mp[val] = nums.size();
        nums.push_back(val);
        return true;
    }

    bool remove(int val) {
        if (!mp.count(val))
            return false;

        int removeIndex = mp[val];
        nums[removeIndex] = nums.back();
        mp[nums.back()] = removeIndex;

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

```

### **...**

```

```

<!-- tabs:end -->
```
