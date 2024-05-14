---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20030.%20%E6%8F%92%E5%85%A5%E3%80%81%E5%88%A0%E9%99%A4%E5%92%8C%E9%9A%8F%E6%9C%BA%E8%AE%BF%E9%97%AE%E9%83%BD%E6%98%AF%20O%281%29%20%E7%9A%84%E5%AE%B9%E5%99%A8/README.md
---

# [剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器](https://leetcode.cn/problems/FortPu)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个支持在<em>平均&nbsp;</em>时间复杂度 <strong>O(1)</strong>&nbsp;下，执行以下操作的数据结构：</p>

<ul>
	<li><code>insert(val)</code>：当元素 <code>val</code> 不存在时返回 <code>true</code>&nbsp;，并向集合中插入该项，否则返回 <code>false</code> 。</li>
	<li><code>remove(val)</code>：当元素 <code>val</code> 存在时返回 <code>true</code>&nbsp;，并从集合中移除该项，否则f返回 <code>true</code>&nbsp;。</li>
	<li><code>getRandom</code>：随机返回现有集合中的一项。每个元素应该有&nbsp;<strong>相同的概率&nbsp;</strong>被返回。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 :</strong></p>

<pre>
<strong>输入: </strong>inputs = [&quot;RandomizedSet&quot;, &quot;insert&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;]
[[], [1], [2], [2], [], [1], [2], []]
<strong>输出: </strong>[null, true, false, true, 2, true, false, 2]
<strong>解释:
</strong>RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入

randomSet.remove(2); // 返回 false，表示集合中不存在 2

randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]

randomSet.getRandom(); // getRandom 应随机返回 1 或 2

randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]

randomSet.insert(2); // 2 已在集合中，所以返回 false

randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li>最多进行<code> 2 * 10<sup>5</sup></code> 次&nbsp;<code>insert</code> ， <code>remove</code> 和 <code>getRandom</code> 方法调用</li>
	<li>当调用&nbsp;<code>getRandom</code> 方法时，集合中至少有一个元素</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 380&nbsp;题相同：<a href="https://leetcode.cn/problems/insert-delete-getrandom-o1/">https://leetcode.cn/problems/insert-delete-getrandom-o1/</a></p>

## 解法

### 方法一

<!-- tabs:start -->

```python
class RandomizedSet:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.a = []
        self.m = {}

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        if val in self.m:
            return False
        self.m[val] = len(self.a)
        self.a.append(val)
        return True

    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val in self.m:
            idx = self.m[val]
            self.a[idx], self.a[-1] = self.a[-1], self.a[idx]
            self.m[self.a[idx]] = idx
            self.a.pop()
            del self.m[val]
            return True
        return False

    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return random.choice(self.a)


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
```

```java
class RandomizedSet {
    private final Map<Integer, Integer> m;
    private final List<Integer> a;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.m = new HashMap<>();
        this.a = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified
     * element.
     */
    public boolean insert(int val) {
        if (this.m.containsKey(val)) {
            return false;
        }
        this.m.put(val, this.a.size());
        this.a.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (this.m.containsKey(val)) {
            int idx = this.m.get(val), last = this.a.size() - 1;
            Collections.swap(this.a, idx, last);
            this.m.put(this.a.get(idx), idx);
            this.a.remove(last);
            this.m.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return this.a.get(ThreadLocalRandom.current().nextInt(this.a.size()));
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

<!-- tabs:end -->

<!-- end -->
