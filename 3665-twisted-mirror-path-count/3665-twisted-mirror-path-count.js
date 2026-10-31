var uniquePaths = function(grid) {
    const m = grid.length;
    const n = grid[0].length;
    const MOD = 1000000007;

    const R_dest = Array.from({ length: m }, () => Array(n).fill(null));
    const D_dest = Array.from({ length: m }, () => Array(n).fill(null));

    for (let i = m - 1; i >= 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (grid[i][j] === 0) {
                R_dest[i][j] = [i, j];
                D_dest[i][j] = [i, j];
            } else {
                if (i + 1 < m) {
                    R_dest[i][j] = D_dest[i + 1][j];
                } else {
                    R_dest[i][j] = null;
                }

                if (j + 1 < n) {
                    D_dest[i][j] = R_dest[i][j + 1];
                } else {
                    D_dest[i][j] = null;
                }
            }
        }
    }

    const dp = Array.from({ length: m }, () => Array(n).fill(0));
    dp[m - 1][n - 1] = 1;

    for (let i = m - 1; i >= 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (i === m - 1 && j === n - 1) continue;
            if (grid[i][j] === 1) continue;

            if (j + 1 < n) {
                const dest = R_dest[i][j + 1];
                if (dest !== null) {
                    dp[i][j] = (dp[i][j] + dp[dest[0]][dest[1]]) % MOD;
                }
            }

            if (i + 1 < m) {
                const dest = D_dest[i + 1][j];
                if (dest !== null) {
                    dp[i][j] = (dp[i][j] + dp[dest[0]][dest[1]]) % MOD;
                }
            }
        }
    }

    return dp[0][0];
};
