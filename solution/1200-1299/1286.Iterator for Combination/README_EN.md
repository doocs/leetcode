# [1286. Iterator for Combination](https://leetcode.com/problems/iterator-for-combination)

[中文文档](/solution/1200-1299/1286.Iterator%20for%20Combination/README.md)

## Description

<p>Design the <code>CombinationIterator</code> class:</p>

<ul>
	<li><code>CombinationIterator(string characters, int combinationLength)</code> Initializes the object with a string <code>characters</code> of <strong>sorted distinct</strong> lowercase English letters and a number <code>combinationLength</code> as arguments.</li>
	<li><code>next()</code> Returns the next combination of length <code>combinationLength</code> in <strong>lexicographical order</strong>.</li>
	<li><code>hasNext()</code> Returns <code>true</code> if and only if there exists a next combination.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;CombinationIterator&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;, &quot;next&quot;, &quot;hasNext&quot;]
[[&quot;abc&quot;, 2], [], [], [], [], [], []]
<strong>Output</strong>
[null, &quot;ab&quot;, true, &quot;ac&quot;, true, &quot;bc&quot;, false]

<strong>Explanation</strong>
CombinationIterator itr = new CombinationIterator(&quot;abc&quot;, 2);
itr.next();    // return &quot;ab&quot;
itr.hasNext(); // return True
itr.next();    // return &quot;ac&quot;
itr.hasNext(); // return True
itr.next();    // return &quot;bc&quot;
itr.hasNext(); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= combinationLength &lt;= characters.length &lt;= 15</code></li>
	<li>All the characters of <code>characters</code> are <strong>unique</strong>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>next</code> and <code>hasNext</code>.</li>
	<li>It is guaranteed that all calls of the function <code>next</code> are valid.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class CombinationIterator:
    def __init__(self, characters: str, combinationLength: int):
        def dfs(i):
            if len(t) == combinationLength:
                cs.append(''.join(t))
                return
            if i == n:
                return
            t.append(characters[i])
            dfs(i + 1)
            t.pop()
            dfs(i + 1)

        cs = []
        n = len(characters)
        t = []
        dfs(0)
        self.cs = cs
        self.idx = 0

    def next(self) -> str:
        ans = self.cs[self.idx]
        self.idx += 1
        return ans

    def hasNext(self) -> bool:
        return self.idx < len(self.cs)


# Your CombinationIterator object will be instantiated and called as such:
# obj = CombinationIterator(characters, combinationLength)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

```python
class CombinationIterator:

    def __init__(self, characters: str, combinationLength: int):
        self.curr = (1 << len(characters)) - 1
        self.size = combinationLength
        self.cs = characters[::-1]

    def next(self) -> str:
        while self.curr >= 0 and self.curr.bit_count() != self.size:
            self.curr -= 1
        ans = []
        for i in range(len(self.cs)):
            if (self.curr >> i) & 1:
                ans.append(self.cs[i])
        self.curr -= 1
        return ''.join(ans[::-1])

    def hasNext(self) -> bool:
        while self.curr >= 0 and self.curr.bit_count() != self.size:
            self.curr -= 1
        return self.curr >= 0


# Your CombinationIterator object will be instantiated and called as such:
# obj = CombinationIterator(characters, combinationLength)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

### **Java**

```java
class CombinationIterator {
    private int n;
    private int combinationLength;
    private String characters;
    private StringBuilder t = new StringBuilder();
    private List<String> cs = new ArrayList<>();
    private int idx = 0;

    public CombinationIterator(String characters, int combinationLength) {
        n = characters.length();
        this.combinationLength = combinationLength;
        this.characters = characters;
        dfs(0);
    }

    public String next() {
        return cs.get(idx++);
    }

    public boolean hasNext() {
        return idx < cs.size();
    }

    private void dfs(int i) {
        if (t.length() == combinationLength) {
            cs.add(t.toString());
            return;
        }
        if (i == n) {
            return;
        }
        t.append(characters.charAt(i));
        dfs(i + 1);
        t.deleteCharAt(t.length() - 1);
        dfs(i + 1);
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

```java
class CombinationIterator {
    private int curr;
    private int size;
    private char[] cs;

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length();
        curr = (1 << n) - 1;
        size = combinationLength;
        cs = new char[n];
        for (int i = 0; i < n; ++i) {
            cs[i] = characters.charAt(n - i - 1);
        }
    }

    public String next() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cs.length; ++i) {
            if (((curr >> i) & 1) == 1) {
                ans.append(cs[i]);
            }
        }
        --curr;
        return ans.reverse().toString();
    }

    public boolean hasNext() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        return curr >= 0;
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

### **C++**

```cpp
class CombinationIterator {
public:
    string characters;
    vector<string> cs;
    int idx;
    int n;
    int combinationLength;
    string t;

    CombinationIterator(string characters, int combinationLength) {
        idx = 0;
        n = characters.size();
        this->characters = characters;
        this->combinationLength = combinationLength;
        dfs(0);
    }

    string next() {
        return cs[idx++];
    }

    bool hasNext() {
        return idx < cs.size();
    }

    void dfs(int i) {
        if (t.size() == combinationLength) {
            cs.push_back(t);
            return;
        }
        if (i == n) return;
        t.push_back(characters[i]);
        dfs(i + 1);
        t.pop_back();
        dfs(i + 1);
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
```

```cpp
class CombinationIterator {
public:
    int size;
    string cs;
    int curr;

    CombinationIterator(string characters, int combinationLength) {
        int n = characters.size();
        curr = (1 << n) - 1;
        reverse(characters.begin(), characters.end());
        cs = characters;
        size = combinationLength;
    }

    string next() {
        while (curr >= 0 && __builtin_popcount(curr) != size) --curr;
        string ans;
        for (int i = 0; i < cs.size(); ++i) {
            if ((curr >> i) & 1) {
                ans += cs[i];
            }
        }
        reverse(ans.begin(), ans.end());
        --curr;
        return ans;
    }

    bool hasNext() {
        while (curr >= 0 && __builtin_popcount(curr) != size) --curr;
        return curr >= 0;
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
```

### **Go**

```go
type CombinationIterator struct {
	cs  []string
	idx int
}

func Constructor(characters string, combinationLength int) CombinationIterator {
	t := []byte{}
	n := len(characters)
	cs := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == combinationLength {
			cs = append(cs, string(t))
			return
		}
		if i == n {
			return
		}
		t = append(t, characters[i])
		dfs(i + 1)
		t = t[:len(t)-1]
		dfs(i + 1)
	}
	dfs(0)
	return CombinationIterator{cs, 0}
}

func (this *CombinationIterator) Next() string {
	ans := this.cs[this.idx]
	this.idx++
	return ans
}

func (this *CombinationIterator) HasNext() bool {
	return this.idx < len(this.cs)
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * obj := Constructor(characters, combinationLength);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
```

```go
type CombinationIterator struct {
	curr int
	size int
	cs   []byte
}

func Constructor(characters string, combinationLength int) CombinationIterator {
	n := len(characters)
	curr := (1 << n) - 1
	size := combinationLength
	cs := make([]byte, n)
	for i := range characters {
		cs[n-i-1] = characters[i]
	}
	return CombinationIterator{curr, size, cs}
}

func (this *CombinationIterator) Next() string {
	for this.curr >= 0 && bits.OnesCount(uint(this.curr)) != this.size {
		this.curr--
	}
	ans := []byte{}
	for i := range this.cs {
		if (this.curr >> i & 1) == 1 {
			ans = append(ans, this.cs[i])
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	this.curr--
	return string(ans)
}

func (this *CombinationIterator) HasNext() bool {
	for this.curr >= 0 && bits.OnesCount(uint(this.curr)) != this.size {
		this.curr--
	}
	return this.curr >= 0
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * obj := Constructor(characters, combinationLength);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
```

### **...**

```

```

<!-- tabs:end -->
