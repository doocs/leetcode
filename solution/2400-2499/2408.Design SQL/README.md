---
comments: true
difficulty: 中等
tags:
    - 设计
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [2408. 设计 SQL](https://leetcode.cn/problems/design-sql)

[English Version](/solution/2400-2499/2408.Design%20SQL/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个字符串数组&nbsp;<code>names</code> 和 <code>columns</code>，大小都为&nbsp;<code>n</code>。其中 <code>names[i]</code> 是第 <code>i</code> 个表的名称，<code>columns[i]</code> 是第 <code>i</code> 个表的列数。</p>

<p>您需要实现一个支持以下&nbsp;<strong>操作&nbsp;</strong>的类：</p>

<ul>
	<li>在特定的表中&nbsp;<strong>插入&nbsp;</strong>一行。插入的每一行都有一个 id。id 是使用自动递增方法分配的，其中第一个插入行的 id 为 1，同一个表中的后续其他行的 id 为上一个插入行的 id (即使它已被删除) 加 1。</li>
	<li>从指定表中&nbsp;<strong>删除&nbsp;</strong>一行。<strong>注意</strong>，删除一行 <strong>不会</strong> 影响下一个插入行的 id。</li>
	<li>从任何表中&nbsp;<strong>查询&nbsp;</strong>一个特定的单元格并返回其值。</li>
	<li>从任何表以 csv 格式 <strong>导出</strong> 所有行。</li>
</ul>

<p>实现&nbsp;<code>SQL</code> 类:</p>

<ul>
	<li><code>SQL(String[] names, int[] columns)</code>

    <ul>
    	<li>创建&nbsp;<code>n</code> 个表。</li>
    </ul>
    </li>
    <li><code>bool ins(String name, String[] row)</code>
    <ul>
    	<li>将 <code>row</code> 插入表 <code>name</code> 中并返回 <code>true</code>。</li>
    	<li>如果&nbsp;<code>row.length</code>&nbsp;<strong>不</strong> 匹配列的预期数量，或者 <code>name</code> <strong>不是</strong> 一个合法的表，不进行任何插入并返回 <code>false</code>。</li>
    </ul>
    </li>
    <li><code>void rmv(String name, int rowId)</code>
    <ul>
    	<li>从表 <code>name</code>&nbsp;中移除行 <code>rowId</code>。</li>
    	<li>如果 <code>name</code> <strong>不是</strong> 一个合法的表或者没有 id 为 <code>rowId</code> 的行，不进行删除。</li>
    </ul>
    </li>
    <li><code>String sel(String name, int rowId, int columnId)</code>
    <ul>
    	<li>返回表 <code>name</code> 中位于特定的 <code>rowId</code> 和 <code>columnId</code> 的单元格的值。</li>
    	<li>如果 name&nbsp;<strong>不是&nbsp;</strong>一个合法的表，或者单元格 <code>(rowId, columnId)</code> <strong>不合法</strong>，返回 <code>"&lt;null&gt;"</code>。</li>
    </ul>
    </li>
    <li><code>String[]&nbsp;exp(String name)</code>
    <ul>
    	<li>返回表 <code>name</code> 中出现的行。</li>
    	<li>如果 <code>name</code> <strong>不是</strong> 一个合法的表，返回一个空数组。每一行以字符串表示，每个单元格的值（<strong>包括</strong> 行的 id）以 <code>","</code> 分隔。</li>
    </ul>
    </li>

</ul>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<pre class="example-io">
["SQL","ins","sel","ins","exp","rmv","sel","exp"]
[[["one","two","three"],[2,3,1]],["two",["first","second","third"]],["two",1,3],["two",["fourth","fifth","sixth"]],["two"],["two",1],["two",2,2],["two"]]
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
[null,true,"third",true,["1,first,second,third","2,fourth,fifth,sixth"],null,"fifth",["2,fourth,fifth,sixth"]]</pre>

<p><strong>解释：</strong></p>

<pre class="example-io">
// 创建 3 张表。
SQL sql = new SQL(["one", "two", "three"], [2, 3, 1]);

// 将 id 为 1 的行添加到表 "two"。返回 True。
sql.ins("two", ["first", "second", "third"]);

// 从表 "two" 中 id 为 1 的行 
// 其中第 3 列返回值 "third"。
sql.sel("two", 1, 3);

// 将另外一个 id 为 2 的行添加到表 "two"。返回 True。
sql.ins("two", ["fourth", "fifth", "sixth"]);

// 导出表 "two" 的行。
// 目前表中有两行 id 为 1 和 2 。
sql.exp("two");

// 删除表 "two" 当中的第一行。注意第二行的 id
// 依然为 2。
sql.rmv("two", 1);

// 从表 "two" 中 id 为 2 的行
// 其中第 2 列返回值 "fifth"。
sql.sel("two", 2, 2);

// 导出表 "two" 的行。
// 目前表中有一行 id 为 2。
sql.exp("two");
</pre>
</div>

<p><strong class="example">示例 2：</strong></p>
<strong>输入：</strong>

<pre>
["SQL","ins","sel","ins","exp","rmv","sel","exp"]
[[["one","two","three"],[2,3,1]],["two",["first","second","third"]],["two",1,3],["two",["fourth","fifth","sixth"]],["two"],["two",1],["two",2,2],["two"]]
</pre>

<strong>输出：</strong>

<pre>
[null,true,"third",true,["1,first,second,third","2,fourth,fifth,sixth"],null,"fifth",["2,fourth,fifth,sixth"]]
</pre>

<strong>解释：</strong>

<pre>
// 创建 3 张表
SQL sQL = new SQL(["one", "two", "three"], [2, 3, 1]); 

// 将 id 为 1 的行添加到表 "two"。返回 True。
sQL.ins("two", ["first", "second", "third"]); 

// 从表 "two" 中 id 为 1 的行
// 其中第 3 列返回值 "third"。
sQL.sel("two", 1, 3); 

// 删除表 "two" 的第一行。
sQL.rmv("two", 1); 

// 返回 "&lt;null&gt;" 因为 id 为 1 的单元格
// 已经从表 "two" 中删除。
sQL.sel("two", 1, 2); 

// 返回 False 因为列的数量不正确。
sQL.ins("two", ["fourth", "fifth"]); 

// 将 id 为 2 的行添加到表 "two"。返回 True。
sQL.ins("two", ["fourth", "fifth", "sixth"]); 
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == names.length == columns.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= names[i].length, row[i].length, name.length &lt;= 10</code></li>
	<li><code>names[i]</code>, <code>row[i]</code>, <code>name</code> 由小写英文字母组成。</li>
	<li><code>1 &lt;= columns[i] &lt;= 10</code></li>
	<li><code>1 &lt;= row.length &lt;= 10</code></li>
	<li>所有的 <code>names[i]</code>&nbsp;都是&nbsp;<strong>不同&nbsp;</strong>的。</li>
	<li>最多调用 <code>ins</code> 和 <code>rmv</code> <code>2000</code> 次。</li>
	<li>最多调用 <code>sel</code> <code>10<sup>4</sup></code>&nbsp;次。</li>
	<li>最多调用 <code>exp</code> <code>500</code> 次。</li>
</ul>

<p><strong>进阶：</strong>如果表因多次删除而变得稀疏，您会选择哪种方法？为什么？考虑对内存使用和性能的影响。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> 每张表都要保留稳定的行号和列数，删除一行之后其余行的编号保持不变。插入和删除最多 $2000$ 次，不必实现完整的关系型引擎。
>
> 若只按下标 $rowId-1$ 存放行，被删单元格仍会被读到，导出也会带上已删除的行。行号在删除之后继续递增，空出来的编号不能再次使用。
>
> 因此我们用表名映射到「行号 → 单元格」的哈希表，同时记下每张表的列数和下一个行号。
>
> $\textit{ins}$ 只在表存在且行宽与列数一致时写入新行。$\textit{rmv}$ 删掉对应行号。$\textit{sel}$ 在表不存在、行已删除或列号越界时返回 $\texttt{<null>}$。$\textit{exp}$ 按行号顺序把仍然存在的行连同行号用逗号拼出来。

<!-- thinking:end -->

分别记录每张表的列数，以及按行号存放的现存数据。直接模拟题目中的 $\textit{ins}$、$\textit{rmv}$、$\textit{sel}$ 和 $\textit{exp}$。

插入、删除和查询的平均时间复杂度为 $O(1)$。导出一张表需要扫描其中仍然存在的行。空间与插入的单元格总数成正比。

<!-- tabs:start -->

#### Python3

```python
class SQL:
    def __init__(self, names: List[str], columns: List[int]):
        self.cols = dict(zip(names, columns))
        self.rows = {name: {} for name in names}
        self.nxt = {name: 1 for name in names}

    def ins(self, name: str, row: List[str]) -> bool:
        if name not in self.cols or len(row) != self.cols[name]:
            return False
        i = self.nxt[name]
        self.rows[name][i] = row
        self.nxt[name] = i + 1
        return True

    def rmv(self, name: str, rowId: int) -> None:
        if name in self.rows:
            self.rows[name].pop(rowId, None)

    def sel(self, name: str, rowId: int, columnId: int) -> str:
        row = self.rows.get(name, {}).get(rowId)
        if row is None or columnId < 1 or columnId > len(row):
            return '<null>'
        return row[columnId - 1]

    def exp(self, name: str) -> List[str]:
        if name not in self.rows:
            return []
        return [
            ','.join([str(i), *self.rows[name][i]]) for i in sorted(self.rows[name])
        ]


# Your SQL object will be instantiated and called as such:
# obj = SQL(names, columns)
# param_1 = obj.ins(name,row)
# obj.rmv(name,rowId)
# param_3 = obj.sel(name,rowId,columnId)
# param_4 = obj.exp(name)
```

#### Java

```java
class SQL {
    private final Map<String, Integer> cols = new HashMap<>();
    private final Map<String, Map<Integer, List<String>>> rows = new HashMap<>();
    private final Map<String, Integer> nxt = new HashMap<>();

    public SQL(String[] names, int[] columns) {
        for (int i = 0; i < names.length; ++i) {
            cols.put(names[i], columns[i]);
            rows.put(names[i], new HashMap<>());
            nxt.put(names[i], 1);
        }
    }

    public boolean ins(String name, String[] row) {
        if (!cols.containsKey(name) || row.length != cols.get(name)) {
            return false;
        }
        int id = nxt.get(name);
        rows.get(name).put(id, Arrays.asList(row));
        nxt.put(name, id + 1);
        return true;
    }

    public void rmv(String name, int rowId) {
        Map<Integer, List<String>> table = rows.get(name);
        if (table != null) {
            table.remove(rowId);
        }
    }

    public String sel(String name, int rowId, int columnId) {
        Map<Integer, List<String>> table = rows.get(name);
        if (table == null || !table.containsKey(rowId)) {
            return "<null>";
        }
        List<String> row = table.get(rowId);
        if (columnId < 1 || columnId > row.size()) {
            return "<null>";
        }
        return row.get(columnId - 1);
    }

    public String[] exp(String name) {
        Map<Integer, List<String>> table = rows.get(name);
        if (table == null) {
            return new String[0];
        }
        List<Integer> ids = new ArrayList<>(table.keySet());
        Collections.sort(ids);
        String[] ans = new String[ids.size()];
        for (int i = 0; i < ids.size(); ++i) {
            int id = ids.get(i);
            ans[i] = id + "," + String.join(",", table.get(id));
        }
        return ans;
    }
}

/**
 * Your SQL object will be instantiated and called as such:
 * SQL obj = new SQL(names, columns);
 * boolean param_1 = obj.ins(name,row);
 * obj.rmv(name,rowId);
 * String param_3 = obj.sel(name,rowId,columnId);
 * String[] param_4 = obj.exp(name);
 */
```

#### C++

```cpp
class SQL {
public:
    unordered_map<string, int> cols;
    unordered_map<string, map<int, vector<string>>> rows;
    unordered_map<string, int> nxt;

    SQL(vector<string>& names, vector<int>& columns) {
        for (int i = 0; i < (int) names.size(); ++i) {
            cols[names[i]] = columns[i];
            nxt[names[i]] = 1;
        }
    }

    bool ins(string name, vector<string> row) {
        if (!cols.count(name) || (int) row.size() != cols[name]) {
            return false;
        }
        int id = nxt[name]++;
        rows[name][id] = std::move(row);
        return true;
    }

    void rmv(string name, int rowId) {
        if (rows.count(name)) {
            rows[name].erase(rowId);
        }
    }

    string sel(string name, int rowId, int columnId) {
        if (!rows.count(name) || !rows[name].count(rowId)) {
            return "<null>";
        }
        auto& row = rows[name][rowId];
        if (columnId < 1 || columnId > (int) row.size()) {
            return "<null>";
        }
        return row[columnId - 1];
    }

    vector<string> exp(string name) {
        vector<string> ans;
        if (!rows.count(name)) {
            return ans;
        }
        for (auto& [id, row] : rows[name]) {
            string s = to_string(id);
            for (auto& cell : row) {
                s += "," + cell;
            }
            ans.push_back(s);
        }
        return ans;
    }
};

/**
 * Your SQL object will be instantiated and called as such:
 * SQL* obj = new SQL(names, columns);
 * bool param_1 = obj->ins(name,row);
 * obj->rmv(name,rowId);
 * string param_3 = obj->sel(name,rowId,columnId);
 * vector<string> param_4 = obj->exp(name);
 */
```

#### Go

```go
type SQL struct {
	cols   map[string]int
	rows   map[string]map[int][]string
	nextId map[string]int
}

func Constructor(names []string, columns []int) SQL {
	cols := map[string]int{}
	rows := map[string]map[int][]string{}
	nextId := map[string]int{}
	for i, name := range names {
		cols[name] = columns[i]
		rows[name] = map[int][]string{}
		nextId[name] = 1
	}
	return SQL{cols, rows, nextId}
}

func (this *SQL) Ins(name string, row []string) bool {
	c, ok := this.cols[name]
	if !ok || len(row) != c {
		return false
	}
	id := this.nextId[name]
	this.nextId[name] = id + 1
	cp := append([]string(nil), row...)
	this.rows[name][id] = cp
	return true
}

func (this *SQL) Rmv(name string, rowId int) {
	if table, ok := this.rows[name]; ok {
		delete(table, rowId)
	}
}

func (this *SQL) Sel(name string, rowId int, columnId int) string {
	table, ok := this.rows[name]
	if !ok {
		return "<null>"
	}
	row, ok := table[rowId]
	if !ok || columnId < 1 || columnId > len(row) {
		return "<null>"
	}
	return row[columnId-1]
}

func (this *SQL) Exp(name string) []string {
	table, ok := this.rows[name]
	if !ok || len(table) == 0 {
		return []string{}
	}
	ids := make([]int, 0, len(table))
	for id := range table {
		ids = append(ids, id)
	}
	sort.Ints(ids)
	ans := make([]string, 0, len(ids))
	for _, id := range ids {
		s := strconv.Itoa(id)
		for _, cell := range table[id] {
			s += "," + cell
		}
		ans = append(ans, s)
	}
	return ans
}

/**
 * Your SQL object will be instantiated and called as such:
 * obj := Constructor(names, columns);
 * param_1 := obj.Ins(name,row);
 * obj.Rmv(name,rowId);
 * param_3 := obj.Sel(name,rowId,columnId);
 * param_4 := obj.Exp(name);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
