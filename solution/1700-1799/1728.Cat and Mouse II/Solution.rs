impl Solution {
    pub fn can_mouse_win(grid: Vec<String>, cat_jump: i32, mouse_jump: i32) -> bool {
        let m = grid.len();
        let n = grid[0].len();

        let grid: Vec<Vec<char>> = grid.iter().map(|s| s.chars().collect()).collect();

        let mut cat_start = 0usize;
        let mut mouse_start = 0usize;
        let mut food = 0usize;

        let dirs = [-1, 0, 1, 0, -1];

        let mut g_mouse = vec![Vec::<usize>::new(); m * n];
        let mut g_cat = vec![Vec::<usize>::new(); m * n];

        for i in 0..m {
            for j in 0..n {
                let c = grid[i][j];
                if c == '#' {
                    continue;
                }

                let v = i * n + j;

                if c == 'C' {
                    cat_start = v;
                } else if c == 'M' {
                    mouse_start = v;
                } else if c == 'F' {
                    food = v;
                }

                for d in 0..4 {
                    let a = dirs[d];
                    let b = dirs[d + 1];

                    for k in 0..=mouse_jump {
                        let x = i as i32 + k * a;
                        let y = j as i32 + k * b;

                        if !(x >= 0
                            && x < m as i32
                            && y >= 0
                            && y < n as i32
                            && grid[x as usize][y as usize] != '#')
                        {
                            break;
                        }

                        g_mouse[v].push((x as usize) * n + y as usize);
                    }

                    for k in 0..=cat_jump {
                        let x = i as i32 + k * a;
                        let y = j as i32 + k * b;

                        if !(x >= 0
                            && x < m as i32
                            && y >= 0
                            && y < n as i32
                            && grid[x as usize][y as usize] != '#')
                        {
                            break;
                        }

                        g_cat[v].push((x as usize) * n + y as usize);
                    }
                }
            }
        }

        use std::collections::VecDeque;

        let n2 = m * n;

        let mut degree = vec![vec![vec![0i32; 2]; n2]; n2];
        let mut ans = vec![vec![vec![0i32; 2]; n2]; n2];

        for i in 0..n2 {
            for j in 0..n2 {
                degree[i][j][0] = g_mouse[i].len() as i32;
                degree[i][j][1] = g_cat[j].len() as i32;
            }
        }

        let mut q = VecDeque::new();

        for i in 0..n2 {
            ans[food][i][1] = 1;
            ans[i][food][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;

            q.push_back((food, i, 1));
            q.push_back((i, food, 0));
            q.push_back((i, i, 0));
            q.push_back((i, i, 1));
        }

        while let Some((m_pos, c_pos, t)) = q.pop_front() {
            let current_ans = ans[m_pos][c_pos][t];

            let pt = t ^ 1;

            if pt == 1 {
                for &pc in &g_cat[c_pos] {
                    if ans[m_pos][pc][1] != 0 {
                        continue;
                    }

                    if pt as i32 == current_ans - 1 {
                        ans[m_pos][pc][1] = current_ans;
                        q.push_back((m_pos, pc, 1));
                    } else {
                        degree[m_pos][pc][1] -= 1;
                        if degree[m_pos][pc][1] == 0 {
                            ans[m_pos][pc][1] = current_ans;
                            q.push_back((m_pos, pc, 1));
                        }
                    }
                }
            } else {
                for &pm in &g_mouse[m_pos] {
                    if ans[pm][c_pos][0] != 0 {
                        continue;
                    }

                    if pt as i32 == current_ans - 1 {
                        ans[pm][c_pos][0] = current_ans;
                        q.push_back((pm, c_pos, 0));
                    } else {
                        degree[pm][c_pos][0] -= 1;
                        if degree[pm][c_pos][0] == 0 {
                            ans[pm][c_pos][0] = current_ans;
                            q.push_back((pm, c_pos, 0));
                        }
                    }
                }
            }
        }

        ans[mouse_start][cat_start][0] == 1
    }
}
