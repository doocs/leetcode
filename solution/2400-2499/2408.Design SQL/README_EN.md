# [2408. Design SQL](https://leetcode.com/problems/design-sql)

[中文文档](/solution/2400-2499/2408.Design%20SQL/README.md)

## Description

<p>You are given <code>n</code> tables represented with two arrays <code>names</code> and <code>columns</code>, where <code>names[i]</code> is the name of the <code>i<sup>th</sup></code> table and <code>columns[i]</code> is the number of columns of the <code>i<sup>th</sup></code> table.</p>

<p>You should be able to perform the following <strong>operations</strong>:</p>

<ul>
	<li><strong>Insert</strong> a row in a specific table. Each row you insert has an id. The id is assigned using an auto-increment method where the id of the first inserted row is 1, and the id of each other row inserted into the same table is the id of the last inserted row (even if it was deleted) plus one.</li>
	<li><strong>Delete</strong> a row from a specific table. <strong>Note</strong> that deleting a row does not affect the id of the next inserted row.</li>
	<li><strong>Select</strong> a specific cell from any table and return its value.</li>
</ul>

<p>Implement the <code>SQL</code> class:</p>

<ul>
	<li><code>SQL(String[] names, int[] columns)</code> Creates the <code>n</code> tables.</li>
	<li><code>void insertRow(String name, String[] row)</code> Adds a row to the table <code>name</code>. It is <strong>guaranteed</strong> that the table will exist, and the size of the array <code>row</code> is equal to the number of columns in the table.</li>
	<li><code>void deleteRow(String name, int rowId)</code> Removes the row <code>rowId</code> from the table <code>name</code>. It is <strong>guaranteed</strong> that the table and row will <strong>exist</strong>.</li>
	<li><code>String selectCell(String name, int rowId, int columnId)</code> Returns the value of the cell in the row <code>rowId</code> and the column <code>columnId</code> from the table <code>name</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SQL&quot;, &quot;insertRow&quot;, &quot;selectCell&quot;, &quot;insertRow&quot;, &quot;deleteRow&quot;, &quot;selectCell&quot;]
[[[&quot;one&quot;, &quot;two&quot;, &quot;three&quot;], [2, 3, 1]], [&quot;two&quot;, [&quot;first&quot;, &quot;second&quot;, &quot;third&quot;]], [&quot;two&quot;, 1, 3], [&quot;two&quot;, [&quot;fourth&quot;, &quot;fifth&quot;, &quot;sixth&quot;]], [&quot;two&quot;, 1], [&quot;two&quot;, 2, 2]]
<strong>Output</strong>
[null, null, &quot;third&quot;, null, null, &quot;fifth&quot;]

<strong>Explanation</strong>
SQL sql = new SQL([&quot;one&quot;, &quot;two&quot;, &quot;three&quot;], [2, 3, 1]); // creates three tables.
sql.insertRow(&quot;two&quot;, [&quot;first&quot;, &quot;second&quot;, &quot;third&quot;]); // adds a row to the table &quot;two&quot;. Its id is 1.
sql.selectCell(&quot;two&quot;, 1, 3); // return &quot;third&quot;, finds the value of the third column in the row with id 1 of the table &quot;two&quot;.
sql.insertRow(&quot;two&quot;, [&quot;fourth&quot;, &quot;fifth&quot;, &quot;sixth&quot;]); // adds another row to the table &quot;two&quot;. Its id is 2.
sql.deleteRow(&quot;two&quot;, 1); // deletes the first row of the table &quot;two&quot;. Note that the second row will still have the id 2.
sql.selectCell(&quot;two&quot;, 2, 2); // return &quot;fifth&quot;, finds the value of the second column in the row with id 2 of the table &quot;two&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == names.length == columns.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= names[i].length, row[i].length, name.length &lt;= 20</code></li>
	<li><code>names[i]</code>, <code>row[i]</code>, and <code>name</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= columns[i] &lt;= 100</code></li>
	<li>All the strings of <code>names</code> are <strong>distinct</strong>.</li>
	<li><code>name</code> exists in the array <code>names</code>.</li>
	<li><code>row.length</code> equals the number of columns in the chosen table.</li>
	<li><code>rowId</code> and <code>columnId</code> will be valid.</li>
	<li>At most <code>250</code> calls will be made to <code>insertRow</code> and <code>deleteRow</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>selectCell</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
