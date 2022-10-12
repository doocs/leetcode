# [1600. Throne Inheritance](https://leetcode.com/problems/throne-inheritance)

[中文文档](/solution/1600-1699/1600.Throne%20Inheritance/README.md)

## Description

<p>A kingdom consists of a king, his children, his grandchildren, and so on. Every once in a while, someone in the family dies or a child is born.</p>

<p>The kingdom has a well-defined order of inheritance that consists of the king as the first member. Let&#39;s define the recursive function <code>Successor(x, curOrder)</code>, which given a person <code>x</code> and the inheritance order so far, returns who should be the next person after <code>x</code> in the order of inheritance.</p>

<pre>
Successor(x, curOrder):
    if x has no children or all of x&#39;s children are in curOrder:
        if x is the king return null
        else return Successor(x&#39;s parent, curOrder)
    else return x&#39;s oldest child who&#39;s not in curOrder
</pre>

<p>For example, assume we have a kingdom that consists of the king, his children Alice and Bob (Alice is older than Bob), and finally Alice&#39;s son Jack.</p>

<ol>
	<li>In the beginning, <code>curOrder</code> will be <code>[&quot;king&quot;]</code>.</li>
	<li>Calling <code>Successor(king, curOrder)</code> will return Alice, so we append to <code>curOrder</code> to get <code>[&quot;king&quot;, &quot;Alice&quot;]</code>.</li>
	<li>Calling <code>Successor(Alice, curOrder)</code> will return Jack, so we append to <code>curOrder</code> to get <code>[&quot;king&quot;, &quot;Alice&quot;, &quot;Jack&quot;]</code>.</li>
	<li>Calling <code>Successor(Jack, curOrder)</code> will return Bob, so we append to <code>curOrder</code> to get <code>[&quot;king&quot;, &quot;Alice&quot;, &quot;Jack&quot;, &quot;Bob&quot;]</code>.</li>
	<li>Calling <code>Successor(Bob, curOrder)</code> will return <code>null</code>. Thus the order of inheritance will be <code>[&quot;king&quot;, &quot;Alice&quot;, &quot;Jack&quot;, &quot;Bob&quot;]</code>.</li>
</ol>

<p>Using the above function, we can always obtain a unique order of inheritance.</p>

<p>Implement the <code>ThroneInheritance</code> class:</p>

<ul>
	<li><code>ThroneInheritance(string kingName)</code> Initializes an object of the <code>ThroneInheritance</code> class. The name of the king is given as part of the constructor.</li>
	<li><code>void birth(string parentName, string childName)</code> Indicates that <code>parentName</code> gave birth to <code>childName</code>.</li>
	<li><code>void death(string name)</code> Indicates the death of <code>name</code>. The death of the person doesn&#39;t affect the <code>Successor</code> function nor the current inheritance order. You can treat it as just marking the person as dead.</li>
	<li><code>string[] getInheritanceOrder()</code> Returns a list representing the current order of inheritance <strong>excluding</strong> dead people.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;ThroneInheritance&quot;, &quot;birth&quot;, &quot;birth&quot;, &quot;birth&quot;, &quot;birth&quot;, &quot;birth&quot;, &quot;birth&quot;, &quot;getInheritanceOrder&quot;, &quot;death&quot;, &quot;getInheritanceOrder&quot;]
[[&quot;king&quot;], [&quot;king&quot;, &quot;andy&quot;], [&quot;king&quot;, &quot;bob&quot;], [&quot;king&quot;, &quot;catherine&quot;], [&quot;andy&quot;, &quot;matthew&quot;], [&quot;bob&quot;, &quot;alex&quot;], [&quot;bob&quot;, &quot;asha&quot;], [null], [&quot;bob&quot;], [null]]
<strong>Output</strong>
[null, null, null, null, null, null, null, [&quot;king&quot;, &quot;andy&quot;, &quot;matthew&quot;, &quot;bob&quot;, &quot;alex&quot;, &quot;asha&quot;, &quot;catherine&quot;], null, [&quot;king&quot;, &quot;andy&quot;, &quot;matthew&quot;, &quot;alex&quot;, &quot;asha&quot;, &quot;catherine&quot;]]

<strong>Explanation</strong>
ThroneInheritance t= new ThroneInheritance(&quot;king&quot;); // order: <strong>king</strong>
t.birth(&quot;king&quot;, &quot;andy&quot;); // order: king &gt; <strong>andy</strong>
t.birth(&quot;king&quot;, &quot;bob&quot;); // order: king &gt; andy &gt; <strong>bob</strong>
t.birth(&quot;king&quot;, &quot;catherine&quot;); // order: king &gt; andy &gt; bob &gt; <strong>catherine</strong>
t.birth(&quot;andy&quot;, &quot;matthew&quot;); // order: king &gt; andy &gt; <strong>matthew</strong> &gt; bob &gt; catherine
t.birth(&quot;bob&quot;, &quot;alex&quot;); // order: king &gt; andy &gt; matthew &gt; bob &gt; <strong>alex</strong> &gt; catherine
t.birth(&quot;bob&quot;, &quot;asha&quot;); // order: king &gt; andy &gt; matthew &gt; bob &gt; alex &gt; <strong>asha</strong> &gt; catherine
t.getInheritanceOrder(); // return [&quot;king&quot;, &quot;andy&quot;, &quot;matthew&quot;, &quot;bob&quot;, &quot;alex&quot;, &quot;asha&quot;, &quot;catherine&quot;]
t.death(&quot;bob&quot;); // order: king &gt; andy &gt; matthew &gt; <strong><s>bob</s></strong> &gt; alex &gt; asha &gt; catherine
t.getInheritanceOrder(); // return [&quot;king&quot;, &quot;andy&quot;, &quot;matthew&quot;, &quot;alex&quot;, &quot;asha&quot;, &quot;catherine&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= kingName.length, parentName.length, childName.length, name.length &lt;= 15</code></li>
	<li><code>kingName</code>, <code>parentName</code>, <code>childName</code>, and <code>name</code> consist of lowercase English letters only.</li>
	<li>All arguments <code>childName</code> and <code>kingName</code> are <strong>distinct</strong>.</li>
	<li>All <code>name</code> arguments of <code>death</code> will be passed to either the constructor or as <code>childName</code> to <code>birth</code> first.</li>
	<li>For each call to&nbsp;<code>birth(parentName, childName)</code>, it is guaranteed that&nbsp;<code>parentName</code> is alive.</li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>birth</code> and <code>death</code>.</li>
	<li>At most <code>10</code> calls will be made to <code>getInheritanceOrder</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class ThroneInheritance:

    def __init__(self, kingName: str):
        self.g = defaultdict(list)
        self.dead = set()
        self.king = kingName

    def birth(self, parentName: str, childName: str) -> None:
        self.g[parentName].append(childName)

    def death(self, name: str) -> None:
        self.dead.add(name)

    def getInheritanceOrder(self) -> List[str]:
        def dfs(x):
            if x not in self.dead:
                ans.append(x)
            for y in self.g[x]:
                dfs(y)

        ans = []
        dfs(self.king)
        return ans

# Your ThroneInheritance object will be instantiated and called as such:
# obj = ThroneInheritance(kingName)
# obj.birth(parentName,childName)
# obj.death(name)
# param_3 = obj.getInheritanceOrder()
```

### **Java**

```java
class ThroneInheritance {
    private Map<String, List<String>> g = new HashMap<>();
    private Set<String> dead = new HashSet<>();
    private List<String> ans;
    private String king;

    public ThroneInheritance(String kingName) {
        king = kingName;
    }

    public void birth(String parentName, String childName) {
        g.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        ans = new ArrayList<>();
        dfs(king);
        return ans;
    }

    private void dfs(String x) {
        if (!dead.contains(x)) {
            ans.add(x);
        }
        for (String y : g.getOrDefault(x, Collections.emptyList())) {
            dfs(y);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
```

### **C++**

```cpp
class ThroneInheritance {
public:
    unordered_map<string, vector<string>> g;
    unordered_set<string> dead;
    string king;
    vector<string> ans;

    ThroneInheritance(string kingName) {
        king = kingName;
    }

    void birth(string parentName, string childName) {
        g[parentName].push_back(childName);
    }

    void death(string name) {
        dead.insert(name);
    }

    vector<string> getInheritanceOrder() {
        ans.resize(0);
        dfs(king);
        return ans;
    }

    void dfs(string& x) {
        if (!dead.count(x)) {
            ans.push_back(x);
        }
        for (auto& y : g[x]) {
            dfs(y);
        }
    }
};

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance* obj = new ThroneInheritance(kingName);
 * obj->birth(parentName,childName);
 * obj->death(name);
 * vector<string> param_3 = obj->getInheritanceOrder();
 */
```

### **Go**

```go
type ThroneInheritance struct {
	g    map[string][]string
	dead map[string]bool
	king string
}

func Constructor(kingName string) ThroneInheritance {
	g := map[string][]string{}
	dead := map[string]bool{}
	return ThroneInheritance{g, dead, kingName}
}

func (this *ThroneInheritance) Birth(parentName string, childName string) {
	this.g[parentName] = append(this.g[parentName], childName)
}

func (this *ThroneInheritance) Death(name string) {
	this.dead[name] = true
}

func (this *ThroneInheritance) GetInheritanceOrder() []string {
	var dfs func(x string)
	ans := []string{}

	dfs = func(x string) {
		if !this.dead[x] {
			ans = append(ans, x)
		}
		for _, y := range this.g[x] {
			dfs(y)
		}
	}
	dfs(this.king)
	return ans
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * obj := Constructor(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * param_3 := obj.GetInheritanceOrder();
 */
```

### **...**

```

```

<!-- tabs:end -->
