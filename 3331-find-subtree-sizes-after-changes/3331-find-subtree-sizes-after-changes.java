import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] ans;
    private int[] lastSeen;
    private int[] parent;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.parent = parent;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        ans[u] = 1;
        int charIdx = s.charAt(u) - 'a';
        
        int newParent = (lastSeen[charIdx] != -1) ? lastSeen[charIdx] : parent[u];

        int prevAncestor = lastSeen[charIdx];
        lastSeen[charIdx] = u;

        for (int v : adj[u]) {
            dfs(v);
        }

        lastSeen[charIdx] = prevAncestor;

        if (newParent != -1) {
            ans[newParent] += ans[u];
        }
    }
}