# [677. 键值映射](https://leetcode.cn/problems/map-sum-pairs)

[English Version](/solution/0600-0699/0677.Map%20Sum%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个 map ，满足以下几点:</p>

<ul>
	<li>字符串表示键，整数表示值</li>
	<li>返回具有前缀等于给定字符串的键的值的总和</li>
</ul>

<p>实现一个 <code>MapSum</code> 类：</p>

<ul>
	<li><code>MapSum()</code> 初始化 <code>MapSum</code> 对象</li>
	<li><code>void insert(String key, int val)</code> 插入 <code>key-val</code> 键值对，字符串表示键 <code>key</code> ，整数表示值 <code>val</code> 。如果键 <code>key</code> 已经存在，那么原来的键值对&nbsp;<code>key-value</code>&nbsp;将被替代成新的键值对。</li>
	<li><code>int sum(string prefix)</code> 返回所有以该前缀 <code>prefix</code> 开头的键 <code>key</code> 的值的总和。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
<strong>输出：</strong>
[null, null, 3, null, 5]

<strong>解释：</strong>
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // 返回 3 (<u>ap</u>ple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // 返回 5 (<u>ap</u>ple + <u>ap</u>p = 3 + 2 = 5)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= key.length, prefix.length &lt;= 50</code></li>
	<li><code>key</code> 和 <code>prefix</code> 仅由小写英文字母组成</li>
	<li><code>1 &lt;= val &lt;= 1000</code></li>
	<li>最多调用 <code>50</code> 次 <code>insert</code> 和 <code>sum</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 前缀树**

我们用哈希表 $d$ 存放键值对，用前缀树 $t$ 存放键值对的前缀和。前缀树的每个节点包含两个信息：

-   `val`：以该节点为前缀的键值对的值的总和
-   `children`：长度为 $26$ 的数组，存放该节点的子节点

插入键值对 $(key, val)$ 时，我们先判断哈希表是否存在该键，如果存在，那么前缀树每个节点的 `val` 都要减去该键原来的值，然后再加上新的值。如果不存在，那么前缀树每个节点的 `val` 都要加上新的值。

查询前缀和时，我们从前缀树的根节点开始，遍历前缀字符串，如果当前节点的子节点中不存在该字符，那么说明前缀树中不存在该前缀，返回 $0$。否则，继续遍历下一个字符，直到遍历完前缀字符串，返回当前节点的 `val`。

时间复杂度方面，插入键值对的时间复杂度为 $O(n)$，其中 $n$ 为键的长度。查询前缀和的时间复杂度为 $O(m)$，其中 $m$ 为前缀的长度。

空间复杂度 $O(n \times m \times C)$，其中 $n$ 和 $m$ 分别是键的数量以及键的最大长度；而 $C$ 是字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.val: int = 0

    def insert(self, w: str, x: int):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.val += x

    def search(self, w: str) -> int:
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return 0
            node = node.children[idx]
        return node.val


class MapSum:

    def __init__(self):
        self.d = defaultdict(int)
        self.tree = Trie()

    def insert(self, key: str, val: int) -> None:
        x = val - self.d[key]
        self.d[key] = val
        self.tree.insert(key, x)

    def sum(self, prefix: str) -> int:
        return self.tree.search(prefix)

# Your MapSum object will be instantiated and called as such:
# obj = MapSum()
# obj.insert(key,val)
# param_2 = obj.sum(prefix)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int val;

    public void insert(String w, int x) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            node.val += x;
        }
    }

    public int search(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                return 0;
            }
            node = node.children[idx];
        }
        return node.val;
    }
}

class MapSum {
    private Map<String, Integer> d = new HashMap<>();
    private Trie trie = new Trie();


    public MapSum() {

    }

    public void insert(String key, int val) {
        int x = val - d.getOrDefault(key, 0);
        d.put(key, val);
        trie.insert(key, x);
    }

    public int sum(String prefix) {
        return trie.search(prefix);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
```

### **C++**

```cpp
class Trie {
public:
    Trie()
        : children(26, nullptr) {
    }

    void insert(string& w, int x) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
            node->val += x;
        }
    }

    int search(string& w) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                return 0;
            }
            node = node->children[c];
        }
        return node->val;
    }

private:
    vector<Trie*> children;
    int val = 0;
};

class MapSum {
public:
    MapSum() {
    }

    void insert(string key, int val) {
        int x = val - d[key];
        d[key] = val;
        trie->insert(key, x);
    }

    int sum(string prefix) {
        return trie->search(prefix);
    }

private:
    unordered_map<string, int> d;
    Trie* trie = new Trie();
};

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum* obj = new MapSum();
 * obj->insert(key,val);
 * int param_2 = obj->sum(prefix);
 */
```

### **Go**

```go
type trie struct {
	children [26]*trie
	val      int
}

func (t *trie) insert(w string, x int) {
	for _, c := range w {
		c -= 'a'
		if t.children[c] == nil {
			t.children[c] = &trie{}
		}
		t = t.children[c]
		t.val += x
	}
}

func (t *trie) search(w string) int {
	for _, c := range w {
		c -= 'a'
		if t.children[c] == nil {
			return 0
		}
		t = t.children[c]
	}
	return t.val
}

type MapSum struct {
	d map[string]int
	t *trie
}

func Constructor() MapSum {
	return MapSum{make(map[string]int), &trie{}}
}

func (this *MapSum) Insert(key string, val int) {
	x := val - this.d[key]
	this.d[key] = val
	this.t.insert(key, x)
}

func (this *MapSum) Sum(prefix string) int {
	return this.t.search(prefix)
}

/**
 * Your MapSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(key,val);
 * param_2 := obj.Sum(prefix);
 */
```

### **TypeScript**

```ts
class Trie {
    children: Trie[];
    val: number;

    constructor() {
        this.children = new Array(26);
        this.val = 0;
    }

    insert(w: string, x: number) {
        let node: Trie = this;
        for (const c of w) {
            const i = c.charCodeAt(0) - 97;
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
            node.val += x;
        }
    }

    search(w: string): number {
        let node: Trie = this;
        for (const c of w) {
            const i = c.charCodeAt(0) - 97;
            if (!node.children[i]) {
                return 0;
            }
            node = node.children[i];
        }
        return node.val;
    }
}

class MapSum {
    d: Map<string, number>;
    t: Trie;
    constructor() {
        this.d = new Map();
        this.t = new Trie();
    }

    insert(key: string, val: number): void {
        const x = val - (this.d.get(key) ?? 0);
        this.d.set(key, val);
        this.t.insert(key, x);
    }

    sum(prefix: string): number {
        return this.t.search(prefix);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * var obj = new MapSum()
 * obj.insert(key,val)
 * var param_2 = obj.sum(prefix)
 */
```

### **...**

```

```

<!-- tabs:end -->
