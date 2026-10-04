---
comments: true
difficulty: Medium
tags:
    - Design
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [2408. Design SQL](https://leetcode.com/problems/design-sql)

[中文文档](/solution/2400-2499/2408.Design%20SQL/README.md)

## Description

<!-- description:start -->

<p>You are given two string arrays, <code>names</code> and <code>columns</code>, both of size <code>n</code>. The <code>i<sup>th</sup></code> table is represented by the name <code>names[i]</code> and contains <code>columns[i]</code> number of columns.</p>

<p>You need to implement a class that supports the following <strong>operations</strong>:</p>

<ul>
	<li><strong>Insert</strong> a row in a specific table with an id assigned using an <em>auto-increment</em> method, where the id of the first inserted row is 1, and the id of each <em>new </em>row inserted into the same table is <strong>one greater</strong> than the id of the <strong>last inserted</strong> row, even if the last row was <em>removed</em>.</li>
	<li><strong>Remove</strong> a row from a specific table. Removing a row <strong>does not</strong> affect the id of the next inserted row.</li>
	<li><strong>Select</strong> a specific cell from any table and return its value.</li>
	<li><strong>Export</strong> all rows from any table in csv format.</li>
</ul>

<p>Implement the <code>SQL</code> class:</p>

<ul>
	<li><code>SQL(String[] names, int[] columns)</code>

    <ul>
    	<li>Creates the <code>n</code> tables.</li>
    </ul>
    </li>
    <li><code>bool ins(String name, String[] row)</code>
    <ul>
    	<li>Inserts <code>row</code> into the table <code>name</code> and returns <code>true</code>.</li>
    	<li>If <code>row.length</code> <strong>does not</strong> match the expected number of columns, or <code>name</code> is <strong>not</strong> a valid table, returns <code>false</code> without any insertion.</li>
    </ul>
    </li>
    <li><code>void rmv(String name, int rowId)</code>
    <ul>
    	<li>Removes the row <code>rowId</code> from the table <code>name</code>.</li>
    	<li>If <code>name</code> is <strong>not</strong> a valid table or there is no row with id <code>rowId</code>, no removal is performed.</li>
    </ul>
    </li>
    <li><code>String sel(String name, int rowId, int columnId)</code>
    <ul>
    	<li>Returns the value of the cell at the specified <code>rowId</code> and <code>columnId</code> in the table <code>name</code>.</li>
    	<li>If <code>name</code> is <strong>not</strong> a valid table, or the cell <code>(rowId, columnId)</code> is <strong>invalid</strong>, returns <code>&quot;&lt;null&gt;&quot;</code>.</li>
    </ul>
    </li>
    <li><code>String[] exp(String name)</code>
    <ul>
    	<li>Returns the rows present in the table <code>name</code>.</li>
    	<li>If name is <strong>not</strong> a valid table, returns an empty array. Each row is represented as a string, with each cell value (<strong>including</strong> the row&#39;s id) separated by a <code>&quot;,&quot;</code>.</li>
    </ul>
    </li>

</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<pre class="example-io">
[&quot;SQL&quot;,&quot;ins&quot;,&quot;sel&quot;,&quot;ins&quot;,&quot;exp&quot;,&quot;rmv&quot;,&quot;sel&quot;,&quot;exp&quot;]
[[[&quot;one&quot;,&quot;two&quot;,&quot;three&quot;],[2,3,1]],[&quot;two&quot;,[&quot;first&quot;,&quot;second&quot;,&quot;third&quot;]],[&quot;two&quot;,1,3],[&quot;two&quot;,[&quot;fourth&quot;,&quot;fifth&quot;,&quot;sixth&quot;]],[&quot;two&quot;],[&quot;two&quot;,1],[&quot;two&quot;,2,2],[&quot;two&quot;]]
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
[null,true,&quot;third&quot;,true,[&quot;1,first,second,third&quot;,&quot;2,fourth,fifth,sixth&quot;],null,&quot;fifth&quot;,[&quot;2,fourth,fifth,sixth&quot;]]</pre>

<p><strong>Explanation:</strong></p>

<pre class="example-io">
// Creates three tables.
SQL sql = new SQL([&quot;one&quot;, &quot;two&quot;, &quot;three&quot;], [2, 3, 1]);

// Adds a row to the table &quot;two&quot; with id 1. Returns True.
sql.ins(&quot;two&quot;, [&quot;first&quot;, &quot;second&quot;, &quot;third&quot;]);

// Returns the value &quot;third&quot; from the third column
// in the row with id 1 of the table &quot;two&quot;.
sql.sel(&quot;two&quot;, 1, 3);

// Adds another row to the table &quot;two&quot; with id 2. Returns True.
sql.ins(&quot;two&quot;, [&quot;fourth&quot;, &quot;fifth&quot;, &quot;sixth&quot;]);

// Exports the rows of the table &quot;two&quot;.
// Currently, the table has 2 rows with ids 1 and 2.
sql.exp(&quot;two&quot;);

// Removes the first row of the table &quot;two&quot;. Note that the second row
// will still have the id 2.
sql.rmv(&quot;two&quot;, 1);

// Returns the value &quot;fifth&quot; from the second column
// in the row with id 2 of the table &quot;two&quot;.
sql.sel(&quot;two&quot;, 2, 2);

// Exports the rows of the table &quot;two&quot;.
// Currently, the table has 1 row with id 2.
sql.exp(&quot;two&quot;);
</pre>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<pre class="example-io">
[&quot;SQL&quot;,&quot;ins&quot;,&quot;sel&quot;,&quot;rmv&quot;,&quot;sel&quot;,&quot;ins&quot;,&quot;ins&quot;]
[[[&quot;one&quot;,&quot;two&quot;,&quot;three&quot;],[2,3,1]],[&quot;two&quot;,[&quot;first&quot;,&quot;second&quot;,&quot;third&quot;]],[&quot;two&quot;,1,3],[&quot;two&quot;,1],[&quot;two&quot;,1,2],[&quot;two&quot;,[&quot;fourth&quot;,&quot;fifth&quot;]],[&quot;two&quot;,[&quot;fourth&quot;,&quot;fifth&quot;,&quot;sixth&quot;]]]
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
[null,true,&quot;third&quot;,null,&quot;&lt;null&gt;&quot;,false,true]
</pre>

<p><strong>Explanation:</strong></p>

<pre class="example-io">
// Creates three tables.
SQL sQL = new SQL([&quot;one&quot;, &quot;two&quot;, &quot;three&quot;], [2, 3, 1]); 

// Adds a row to the table &quot;two&quot; with id 1. Returns True. 
sQL.ins(&quot;two&quot;, [&quot;first&quot;, &quot;second&quot;, &quot;third&quot;]); 

// Returns the value &quot;third&quot; from the third column 
// in the row with id 1 of the table &quot;two&quot;.
sQL.sel(&quot;two&quot;, 1, 3); 

// Removes the first row of the table &quot;two&quot;.
sQL.rmv(&quot;two&quot;, 1); 

// Returns &quot;&lt;null&gt;&quot; as the cell with id 1 
// has been removed from table &quot;two&quot;.
sQL.sel(&quot;two&quot;, 1, 2); 

// Returns False as number of columns are not correct.
sQL.ins(&quot;two&quot;, [&quot;fourth&quot;, &quot;fifth&quot;]); 

// Adds a row to the table &quot;two&quot; with id 2. Returns True.
sQL.ins(&quot;two&quot;, [&quot;fourth&quot;, &quot;fifth&quot;, &quot;sixth&quot;]); 
</pre>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == names.length == columns.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= names[i].length, row[i].length, name.length &lt;= 10</code></li>
	<li><code>names[i]</code>, <code>row[i]</code>, and <code>name</code> consist only of lowercase English letters.</li>
	<li><code>1 &lt;= columns[i] &lt;= 10</code></li>
	<li><code>1 &lt;= row.length &lt;= 10</code></li>
	<li>All <code>names[i]</code> are <strong>distinct</strong>.</li>
	<li>At most <code>2000</code> calls will be made to <code>ins</code> and <code>rmv</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>sel</code>.</li>
	<li>At most <code>500</code> calls will be made to <code>exp</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Which approach would you choose if the table might become sparse due to many deletions, and why? Consider the impact on memory usage and performance.

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

<!-- thinking:start -->

> **Thinking**
>
> Each table needs a stable row id, a column count, and a way to drop a row without renumbering the others. At most $2000$ insertions and deletions occur, so a relational engine is unnecessary.
>
> A dense list indexed by $rowId-1$ still returns a deleted cell, and $\textit{exp}$ would keep that row. Ids also keep increasing after a removal, so a freed slot cannot be reused.
>
> Map each table name to the cells keyed by row id, and store the column count together with the next id.
>
> $\textit{ins}$ appends a row only when the table exists and the width matches. $\textit{rmv}$ drops that id. $\textit{sel}$ returns $\texttt{<null>}$ when the table, row, or column is missing. $\textit{exp}$ walks the surviving ids in order and joins each row with its id.

<!-- thinking:end -->

Record the column count of each table, and store live rows in a hash map keyed by row id. Simulate $\textit{ins}$, $\textit{rmv}$, $\textit{sel}$, and $\textit{exp}$ directly.

Insertion, deletion, and selection are $O(1)$ on average. Exporting a table scans the rows that are still present. The space is proportional to the number of inserted cells.

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
