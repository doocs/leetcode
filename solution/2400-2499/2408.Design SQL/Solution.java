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
