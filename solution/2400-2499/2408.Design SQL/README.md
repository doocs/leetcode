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

# [2408. 设计 SQL 🔒](https://leetcode.cn/problems/design-sql)

[English Version](/solution/2400-2499/2408.Design%20SQL/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定 <code>n</code> 个表，用两个数组 <code>names</code> 和 <code>columns</code>&nbsp;表示，其中 <code>names[i]</code> 是第 <code>i</code> 个表的名称，<code>columns[i]</code> 是第 <code>i</code> 个表的列数。</p>

<p>您能够执行以下&nbsp;<strong>操作</strong>:</p>

<ul>
	<li>在特定的表中&nbsp;<strong>插入&nbsp;</strong>一行。插入的每一行都有一个 id。id 是使用自动递增方法分配的，其中第一个插入行的 id 为 1，插入到同一个表中的其他行的 id 为最后一个插入行的id (即使它已被删除) 加1。</li>
	<li>从指定表中&nbsp;<strong>删除&nbsp;</strong>一行。<strong>注意</strong>，删除一行不会影响下一个插入行的 id。</li>
	<li>从任何表中&nbsp;<strong>查询&nbsp;</strong>一个特定的单元格并返回其值。</li>
</ul>

<p>实现&nbsp;<code>SQL</code> 类:</p>

<ul>
	<li><code>SQL(String[] names, int[] columns)</code> 创造&nbsp;<code>n</code> 个表。</li>
	<li><code>void insertRow(String name, String[] row)</code> 向表 <code>name</code>&nbsp;中添加一行。<strong>保证&nbsp;</strong>表存在，并且数组 <code>row</code> 的大小等于表中的列数。</li>
	<li><code>void deleteRow(String name, int rowId)</code> 从表 <code>name</code>&nbsp;中移除行 <code>rowId</code>&nbsp;。<strong>保证&nbsp;</strong>表和行都&nbsp;<strong>存在</strong>。</li>
	<li><code>String selectCell(String name, int rowId, int columnId)</code> 返回表 <code>name</code> 中 <code>rowId</code> 行和 <code>columnId</code> 列中的单元格值。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入</strong>
["SQL", "insertRow", "selectCell", "insertRow", "deleteRow", "selectCell"]
[[["one", "two", "three"], [2, 3, 1]], ["two", ["first", "second", "third"]], ["two", 1, 3], ["two", ["fourth", "fifth", "sixth"]], ["two", 1], ["two", 2, 2]]
<strong>输出</strong>
[null, null, "third", null, null, "fifth"]

<strong>解释</strong>
SQL sql = new SQL(["one", "two", "three"], [2, 3, 1]); // 创建三个表。
sql.insertRow("two", ["first", "second", "third"]); // 向表 "2" 添加一行。id 是 1。
sql.selectCell("two", 1, 3); // 返回 "third"，查找表 "two" 中 id 为 1 的行中第三列的值。
sql.insertRow("two", ["fourth", "fifth", "sixth"]); // 将另一行添加到表 "2" 中。它的 id 是 2。
sql.deleteRow("two", 1); // 删除表 "two" 的第一行。注意，第二行仍然有 id 2。
sql.selectCell("two", 2, 2); // 返回 "fifth"，查找表 "two" 中 id 为 2 的行中第二列的值。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == names.length == columns.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= names[i].length, row[i].length, name.length &lt;= 20</code></li>
	<li><code>names[i]</code>, <code>row[i]</code>, <code>name</code> 由小写英文字母组成。</li>
	<li><code>1 &lt;= columns[i] &lt;= 100</code></li>
	<li>所有的 <code>names</code> 字符串都是&nbsp;<strong>不同&nbsp;</strong>的。</li>
	<li><code>name</code> 存在于&nbsp;<code>names</code>.</li>
	<li><code>row.length</code> 等于所选表中的列数。</li>
	<li><code>rowId</code> 和&nbsp;<code>columnId</code> 是有效的值。</li>
	<li>最多&nbsp;<code>250</code>&nbsp;次调用&nbsp;<code>insertRow</code> 和&nbsp;<code>deleteRow</code>&nbsp;。</li>
	<li><code><font color="#333333"><font face="Helvetica Neue, Helvetica, Arial, sans-serif"><span style="font-size:14px"><span style="background-color:#ffffff">最多&nbsp;</span></span></font></font>10<sup>4</sup></code> 次调用&nbsp;<code>selectCell</code>。</li>
</ul>

## 解法

### 方法一：哈希表

创建哈希表 `tables` 用于存储表名和表数据行的映射。直接模拟题目中的操作即可。

每个操作的时间复杂度均为 $O(1)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

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

<!-- end -->
