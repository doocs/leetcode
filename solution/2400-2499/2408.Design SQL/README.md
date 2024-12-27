---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2408.Design%20SQL/README.md
tags:
    - 设计
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [2408. 设计 SQL 🔒](https://leetcode.cn/problems/design-sql)

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
    <li><code>void rmv(String name, int rowId, int columnId)</code>
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

创建哈希表 `tables` 用于存储表名和表数据行的映射。直接模拟题目中的操作即可。

每个操作的时间复杂度均为 $O(1)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class SQL:
    def __init__(self, names: List[str], columns: List[int]):
        self.tables = defaultdict(list)

    def insertRow(self, name: str, row: List[str]) -> None:
        self.tables[name].append(row)

    def deleteRow(self, name: str, rowId: int) -> None:
        pass

    def selectCell(self, name: str, rowId: int, columnId: int) -> str:
        return self.tables[name][rowId - 1][columnId - 1]


# Your SQL object will be instantiated and called as such:
# obj = SQL(names, columns)
# obj.insertRow(name,row)
# obj.deleteRow(name,rowId)
# param_3 = obj.selectCell(name,rowId,columnId)
```

#### Java

```java
class SQL {
    private Map<String, List<List<String>>> tables;

    public SQL(List<String> names, List<Integer> columns) {
        tables = new HashMap<>(names.size());
    }

    public void insertRow(String name, List<String> row) {
        tables.computeIfAbsent(name, k -> new ArrayList<>()).add(row);
    }

    public void deleteRow(String name, int rowId) {
    }

    public String selectCell(String name, int rowId, int columnId) {
        return tables.get(name).get(rowId - 1).get(columnId - 1);
    }
}

/**
 * Your SQL object will be instantiated and called as such:
 * SQL obj = new SQL(names, columns);
 * obj.insertRow(name,row);
 * obj.deleteRow(name,rowId);
 * String param_3 = obj.selectCell(name,rowId,columnId);
 */
```

#### C++

```cpp
class SQL {
public:
    unordered_map<string, vector<vector<string>>> tables;
    SQL(vector<string>& names, vector<int>& columns) {
    }

    void insertRow(string name, vector<string> row) {
        tables[name].push_back(row);
    }

    void deleteRow(string name, int rowId) {
    }

    string selectCell(string name, int rowId, int columnId) {
        return tables[name][rowId - 1][columnId - 1];
    }
};

/**
 * Your SQL object will be instantiated and called as such:
 * SQL* obj = new SQL(names, columns);
 * obj->insertRow(name,row);
 * obj->deleteRow(name,rowId);
 * string param_3 = obj->selectCell(name,rowId,columnId);
 */
```

#### Go

```go
type SQL struct {
	tables map[string][][]string
}

func Constructor(names []string, columns []int) SQL {
	return SQL{map[string][][]string{}}
}

func (this *SQL) InsertRow(name string, row []string) {
	this.tables[name] = append(this.tables[name], row)
}

func (this *SQL) DeleteRow(name string, rowId int) {

}

func (this *SQL) SelectCell(name string, rowId int, columnId int) string {
	return this.tables[name][rowId-1][columnId-1]
}

/**
 * Your SQL object will be instantiated and called as such:
 * obj := Constructor(names, columns);
 * obj.InsertRow(name,row);
 * obj.DeleteRow(name,rowId);
 * param_3 := obj.SelectCell(name,rowId,columnId);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
