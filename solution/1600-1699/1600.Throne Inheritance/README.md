---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1600.Throne%20Inheritance/README.md
rating: 1768
tags:
    - 树
    - 深度优先搜索
    - 设计
    - 哈希表
---

# [1600. 王位继承顺序](https://leetcode.cn/problems/throne-inheritance)

[English Version](/solution/1600-1699/1600.Throne%20Inheritance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。</p>

<p>这个王国有一个明确规定的王位继承顺序，第一继承人总是国王自己。我们定义递归函数&nbsp;<code>Successor(x, curOrder)</code>&nbsp;，给定一个人&nbsp;<code>x</code>&nbsp;和当前的继承顺序，该函数返回 <code>x</code>&nbsp;的下一继承人。</p>

<pre>
Successor(x, curOrder):
    如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
        如果 x 是国王，那么返回 null
        否则，返回 Successor(x 的父亲, curOrder)
    否则，返回 x 不在 curOrder 中最年长的孩子
</pre>

<p>比方说，假设王国由国王，他的孩子&nbsp;Alice 和 Bob （Alice 比 Bob&nbsp;年长）和 Alice 的孩子&nbsp;Jack 组成。</p>

<ol>
	<li>一开始，&nbsp;<code>curOrder</code>&nbsp;为&nbsp;<code>["king"]</code>.</li>
	<li>调用&nbsp;<code>Successor(king, curOrder)</code>&nbsp;，返回 Alice ，所以我们将 Alice 放入 <code>curOrder</code>&nbsp;中，得到&nbsp;<code>["king", "Alice"]</code>&nbsp;。</li>
	<li>调用&nbsp;<code>Successor(Alice, curOrder)</code>&nbsp;，返回 Jack ，所以我们将 Jack 放入&nbsp;<code>curOrder</code>&nbsp;中，得到&nbsp;<code>["king", "Alice", "Jack"]</code>&nbsp;。</li>
	<li>调用&nbsp;<code>Successor(Jack, curOrder)</code>&nbsp;，返回 Bob ，所以我们将 Bob 放入&nbsp;<code>curOrder</code>&nbsp;中，得到&nbsp;<code>["king", "Alice", "Jack", "Bob"]</code>&nbsp;。</li>
	<li>调用&nbsp;<code>Successor(Bob, curOrder)</code>&nbsp;，返回&nbsp;<code>null</code>&nbsp;。最终得到继承顺序为&nbsp;<code>["king", "Alice", "Jack", "Bob"]</code>&nbsp;。</li>
</ol>

<p>通过以上的函数，我们总是能得到一个唯一的继承顺序。</p>

<p>请你实现&nbsp;<code>ThroneInheritance</code>&nbsp;类：</p>

<ul>
	<li><code>ThroneInheritance(string kingName)</code> 初始化一个&nbsp;<code>ThroneInheritance</code>&nbsp;类的对象。国王的名字作为构造函数的参数传入。</li>
	<li><code>void birth(string parentName, string childName)</code>&nbsp;表示&nbsp;<code>parentName</code>&nbsp;新拥有了一个名为&nbsp;<code>childName</code>&nbsp;的孩子。</li>
	<li><code>void death(string name)</code>&nbsp;表示名为&nbsp;<code>name</code>&nbsp;的人死亡。一个人的死亡不会影响&nbsp;<code>Successor</code>&nbsp;函数，也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。</li>
	<li><code>string[] getInheritanceOrder()</code>&nbsp;返回 <strong>除去</strong>&nbsp;死亡人员的当前继承顺序列表。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
[["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
<strong>输出：</strong>
[null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]

<strong>解释：</strong>
ThroneInheritance t= new ThroneInheritance("king"); // 继承顺序：<strong>king</strong>
t.birth("king", "andy"); // 继承顺序：king &gt; <strong>andy</strong>
t.birth("king", "bob"); // 继承顺序：king &gt; andy &gt; <strong>bob</strong>
t.birth("king", "catherine"); // 继承顺序：king &gt; andy &gt; bob &gt; <strong>catherine</strong>
t.birth("andy", "matthew"); // 继承顺序：king &gt; andy &gt; <strong>matthew</strong> &gt; bob &gt; catherine
t.birth("bob", "alex"); // 继承顺序：king &gt; andy &gt; matthew &gt; bob &gt; <strong>alex</strong> &gt; catherine
t.birth("bob", "asha"); // 继承顺序：king &gt; andy &gt; matthew &gt; bob &gt; alex &gt; <strong>asha</strong> &gt; catherine
t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
t.death("bob"); // 继承顺序：king &gt; andy &gt; matthew &gt; <strong>bob（已经去世）</strong>&gt; alex &gt; asha &gt; catherine
t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= kingName.length, parentName.length, childName.length, name.length &lt;= 15</code></li>
	<li><code>kingName</code>，<code>parentName</code>，&nbsp;<code>childName</code>&nbsp;和&nbsp;<code>name</code>&nbsp;仅包含小写英文字母。</li>
	<li>所有的参数&nbsp;<code>childName</code> 和&nbsp;<code>kingName</code>&nbsp;<strong>互不相同</strong>。</li>
	<li>所有&nbsp;<code>death</code>&nbsp;函数中的死亡名字 <code>name</code>&nbsp;要么是国王，要么是已经出生了的人员名字。</li>
	<li>每次调用 <code>birth(parentName, childName)</code> 时，测试用例都保证 <code>parentName</code> 对应的人员是活着的。</li>
	<li>最多调用&nbsp;<code>10<sup>5</sup></code>&nbsp;次<code>birth</code> 和&nbsp;<code>death</code>&nbsp;。</li>
	<li>最多调用&nbsp;<code>10</code>&nbsp;次&nbsp;<code>getInheritanceOrder</code>&nbsp;。</li>
</ul>

## 解法

### 方法一：多叉树的前序遍历

根据题目描述，我们可以发现，王位继承顺序实际上是一个多叉树的前序遍历。我们可以使用一个哈希表 $g$ 存储每个人的孩子，使用一个集合 $dead$ 存储已经去世的人。

-   调用 `birth(parentName, childName)` 时，我们将 `childName` 添加到 `parentName` 的孩子列表中。
-   调用 `death(name)` 时，我们将 `name` 添加到 `dead` 集合中。
-   调用 `getInheritanceOrder()` 时，我们从国王开始进行深度优先搜索，如果当前节点 `x` 没有去世，我们将 `x` 添加到答案列表中，然后递归地遍历 `x` 的所有孩子。

时间复杂度方面，`birth` 和 `death` 的时间复杂度均为 $O(1)$，`getInheritanceOrder` 的时间复杂度为 $O(n)$，空间复杂度为 $O(n)$。其中 $n$ 是节点数量。

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
