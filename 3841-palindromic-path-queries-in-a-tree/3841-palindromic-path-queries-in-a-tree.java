import java.util.*;

class Solution {
    private int[] parent, depth, heavy, head, pos;
    private int curPos;
    private List<Integer>[] adj;
    private int[] segTree;
    private char[] treeChars;
    private int n;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);
        curPos = 0;

        dfsSize(0, -1, 0);
        decompose(0, 0);

        treeChars = new char[n];
        for (int i = 0; i < n; i++) {
            treeChars[pos[i]] = s.charAt(i);
        }

        segTree = new int[4 * n];
        buildSegTree(1, 0, n - 1);

        List<Boolean> ansList = new ArrayList<>();
        for (String q : queries) {
            String[] tokens = q.split(" ");
            if (tokens[0].equals("update")) {
                int node = Integer.parseInt(tokens[1]);
                char c = tokens[2].charAt(0);
                update(1, 0, n - 1, pos[node], c);
            } else {
                int u = Integer.parseInt(tokens[1]);
                int v = Integer.parseInt(tokens[2]);
                int pathMask = queryPath(u, v);
                ansList.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return ansList;
    }

    private int dfsSize(int v, int p, int d) {
        parent[v] = p;
        depth[v] = d;
        int size = 1;
        int maxSubtreeSize = 0;
        for (int neighbor : adj[v]) {
            if (neighbor != p) {
                int subtreeSize = dfsSize(neighbor, v, d + 1);
                size += subtreeSize;
                if (subtreeSize > maxSubtreeSize) {
                    maxSubtreeSize = subtreeSize;
                    heavy[v] = neighbor;
                }
            }
        }
        return size;
    }

    private void decompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;
        if (heavy[v] != -1) {
            decompose(heavy[v], h);
        }
        for (int neighbor : adj[v]) {
            if (neighbor != parent[v] && neighbor != heavy[v]) {
                decompose(neighbor, neighbor);
            }
        }
    }

    private void buildSegTree(int node, int start, int end) {
        if (start == end) {
            segTree[node] = 1 << (treeChars[start] - 'a');
            return;
        }
        int mid = (start + end) / 2;
        buildSegTree(2 * node, start, mid);
        buildSegTree(2 * node + 1, mid + 1, end);
        segTree[node] = segTree[2 * node] ^ segTree[2 * node + 1];
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            segTree[node] = 1 << (c - 'a');
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        segTree[node] = segTree[2 * node] ^ segTree[2 * node + 1];
    }

    private int querySegTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return segTree[node];
        int mid = (start + end) / 2;
        int leftXor = querySegTree(2 * node, start, mid, l, r);
        int rightXor = querySegTree(2 * node + 1, mid + 1, end, l, r);
        return leftXor ^ rightXor;
    }

    private int queryPath(int u, int v) {
        int resMask = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            resMask ^= querySegTree(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        resMask ^= querySegTree(1, 0, n - 1, pos[u], pos[v]);
        return resMask;
    }
}
