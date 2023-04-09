# [677. Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs)

[中文文档](/solution/0600-0699/0677.Map%20Sum%20Pairs/README.md)

## Description

<p>Design a map that allows you to do the following:</p>

<ul>
	<li>Maps a string key to a given value.</li>
	<li>Returns the sum of the values that have a key with a prefix equal to a given string.</li>
</ul>

<p>Implement the <code>MapSum</code> class:</p>

<ul>
	<li><code>MapSum()</code> Initializes the <code>MapSum</code> object.</li>
	<li><code>void insert(String key, int val)</code> Inserts the <code>key-val</code> pair into the map. If the <code>key</code> already existed, the original <code>key-value</code> pair will be overridden to the new one.</li>
	<li><code>int sum(string prefix)</code> Returns the sum of all the pairs&#39; value whose <code>key</code> starts with the <code>prefix</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MapSum&quot;, &quot;insert&quot;, &quot;sum&quot;, &quot;insert&quot;, &quot;sum&quot;]
[[], [&quot;apple&quot;, 3], [&quot;ap&quot;], [&quot;app&quot;, 2], [&quot;ap&quot;]]
<strong>Output</strong>
[null, null, 3, null, 5]

<strong>Explanation</strong>
MapSum mapSum = new MapSum();
mapSum.insert(&quot;apple&quot;, 3);  
mapSum.sum(&quot;ap&quot;);           // return 3 (<u>ap</u>ple = 3)
mapSum.insert(&quot;app&quot;, 2);    
mapSum.sum(&quot;ap&quot;);           // return 5 (<u>ap</u>ple + <u>ap</u>p = 3 + 2 = 5)
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= key.length, prefix.length &lt;= 50</code></li>
	<li><code>key</code> and <code>prefix</code> consist of only lowercase English letters.</li>
	<li><code>1 &lt;= val &lt;= 1000</code></li>
	<li>At most <code>50</code> calls will be made to <code>insert</code> and <code>sum</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
