---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1600.Throne%20Inheritance/README_EN.md
rating: 1768
tags:
    - Tree
    - Depth-First Search
    - Design
    - Hash Table
---

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

### Solution 1: Preorder Traversal of a Multi-branch Tree

According to the problem description, we can find that the order of throne inheritance is actually a preorder traversal of a multi-branch tree. We can use a hash table $g$ to store the children of each person, and a set $dead$ to store the people who have died.

-   When calling `birth(parentName, childName)`, we add `childName` to the child list of `parentName`.
-   When calling `death(name)`, we add `name` to the `dead` set.
-   When calling `getInheritanceOrder()`, we start a depth-first search from the king. If the current node `x` is not dead, we add `x` to the answer list, and then recursively traverse all children of `x`.

In terms of time complexity, both `birth` and `death` have a time complexity of $O(1)$, and `getInheritanceOrder` has a time complexity of $O(n)$. The space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

```python
class ThroneInheritance:

    def __init__(self, kingName: str):
        self.king = kingName
        self.dead = set()
        self.g = defaultdict(list)

    def birth(self, parentName: str, childName: str) -> None:
        self.g[parentName].append(childName)

    def death(self, name: str) -> None:
        self.dead.add(name)

    def getInheritanceOrder(self) -> List[str]:
        def dfs(x: str):
            x not in self.dead and ans.append(x)
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

```java
class ThroneInheritance {
    private String king;
    private Set<String> dead = new HashSet<>();
    private Map<String, List<String>> g = new HashMap<>();
    private List<String> ans = new ArrayList<>();

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
        ans.clear();
        dfs(king);
        return ans;
    }

    private void dfs(String x) {
        if (!dead.contains(x)) {
            ans.add(x);
        }
        for (String y : g.getOrDefault(x, List.of())) {
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

```cpp
class ThroneInheritance {
public:
    ThroneInheritance(string kingName) {
        king = kingName;
    }

    void birth(string parentName, string childName) {
        g[parentName].emplace_back(childName);
    }

    void death(string name) {
        dead.insert(name);
    }

    vector<string> getInheritanceOrder() {
        ans.resize(0);
        dfs(king);
        return ans;
    }

private:
    string king;
    unordered_set<string> dead;
    unordered_map<string, vector<string>> g;
    vector<string> ans;

    void dfs(string& x) {
        if (!dead.contains(x)) {
            ans.emplace_back(x);
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

```go
type ThroneInheritance struct {
	king string
	dead map[string]bool
	g map[string][]string
}


func Constructor(kingName string) ThroneInheritance {
	return ThroneInheritance{kingName, map[string]bool{}, map[string][]string{}}
}


func (this *ThroneInheritance) Birth(parentName string, childName string)  {
	this.g[parentName] = append(this.g[parentName], childName)
}


func (this *ThroneInheritance) Death(name string)  {
	this.dead[name] = true
}


func (this *ThroneInheritance) GetInheritanceOrder() (ans []string) {
	var dfs func(string)
	dfs = func(x string) {
		if !this.dead[x] {
			ans = append(ans, x)
		}
		for _, y := range this.g[x] {
			dfs(y)
		}
	}
	dfs(this.king)
	return
}


/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * obj := Constructor(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * param_3 := obj.GetInheritanceOrder();
 */
```

```ts
class ThroneInheritance {
    private king: string;
    private dead: Set<string> = new Set();
    private g: Map<string, string[]> = new Map();

    constructor(kingName: string) {
        this.king = kingName;
    }

    birth(parentName: string, childName: string): void {
        this.g.set(parentName, this.g.get(parentName) || []);
        this.g.get(parentName)!.push(childName);
    }

    death(name: string): void {
        this.dead.add(name);
    }

    getInheritanceOrder(): string[] {
        const ans: string[] = [];
        const dfs = (x: string) => {
            if (!this.dead.has(x)) {
                ans.push(x);
            }
            for (const y of this.g.get(x) || []) {
                dfs(y);
            }
        };
        dfs(this.king);
        return ans;
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * var obj = new ThroneInheritance(kingName)
 * obj.birth(parentName,childName)
 * obj.death(name)
 * var param_3 = obj.getInheritanceOrder()
 */
```

```cs
public class ThroneInheritance {
    private string king;
    private HashSet<string> dead = new HashSet<string>();
    private Dictionary<string, List<string>> g = new Dictionary<string, List<string>>();
    private List<string> ans = new List<string>();

    public ThroneInheritance(string kingName) {
        king = kingName;
    }

    public void Birth(string parentName, string childName) {
        if (!g.ContainsKey(parentName)) {
            g[parentName] = new List<string>();
        }
        g[parentName].Add(childName);
    }

    public void Death(string name) {
        dead.Add(name);
    }

    public IList<string> GetInheritanceOrder() {
        ans.Clear();
        DFS(king);
        return ans;
    }

    private void DFS(string x) {
        if (!dead.Contains(x)) {
            ans.Add(x);
        }
        if (g.ContainsKey(x)) {
            foreach (string y in g[x]) {
                DFS(y);
            }
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * IList<string> param_3 = obj.GetInheritanceOrder();
 */
```

<!-- tabs:end -->

<!-- end -->
