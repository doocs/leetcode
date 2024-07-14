class Solution {
    private var dirs = [-1, 0, 1, 0, -1]
    private var image: [[Int]] = []
    private var nc: Int = 0
    private var oc: Int = 0

    func floodFill(_ image: inout [[Int]], _ sr: Int, _ sc: Int, _ newColor: Int) -> [[Int]] {
        self.nc = newColor
        self.oc = image[sr][sc]
        self.image = image
        dfs(sr, sc)
        return self.image
    }

    private func dfs(_ i: Int, _ j: Int) {
        if i < 0 || i >= image.count || j < 0 || j >= image[0].count || image[i][j] != oc || image[i][j] == nc {
            return
        }
        image[i][j] = nc
        for k in 0..<4 {
            dfs(i + dirs[k], j + dirs[k + 1])
        }
    }
}
