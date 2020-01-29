import java.util.Queue;

class Node {
    // (x, y) represents matrix cell coordinates
    // dist represent its minimum distance from the source
    int x, y, dist;
    Queue<Node> path;

    Node(int x, int y, int dist, Queue<Node> path) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.path = path;

    }
};

