import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

public class JTetris extends javax.swing.JFrame {
    static Image IMAGE;
    static Font FONT;

    static {
        ZipInputStream zipImage = new ZipInputStream(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(
            "UEsDBBQAAAAIABmiWkRZ0oOouwAAAJYKAAAFAAAAYy5ibXBz8p3GxQAGZUCsAcQ3gFgCiBkZWMDiCkB5GBCA0iJyLAwl+3oYYpJsGK58u8NAKjBGAgyUgVG7QHYxKUGBAjDiBJUUQUhQAMQGA3xsFL3MMBMNRrpdEABhQxQLCiDYmHZBxCF2QdhAu8CyEHLk2cWkBJSH" +
            "xxcoCgyg5hsbAgWhbBgJUg9Wg24XWDGQHJF2QeOCUruQwEi0S0kRe5qHKCY2zQsgkv0ItAsIKC97EQnecGTYRQUwKNsbNAYAUEsBAj8AFAAAAAgAGaJaRFnSg6i7AAAAlgoAAAUAJAAAAAAAAAAgAAAAAAAAAGMuYm1wCgAgAAAAAAABABgAkJCiSyczzwGQkKJLJzPP" +
            "AZCQoksnM88BUEsFBgAAAAABAAEAVwAAAN4AAAAAAA=="
        )));

        ZipInputStream zipFont = new ZipInputStream(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(
            "UEsDBBQAAAAIAEq5UEN0MGyWoggAAHgrAAAFAAAAYS50dGbklsvvC1EUx78zU+/3OxHiEuLd1jMlxKNR70cQCxumY9oOnU5Np6osdIGEDYmwZGElkYh/gI2FBUIsvFdILNgh8Rxft4d6RyxEYvq7PZ97es73nHNv8mthAOiJFiyotRtS0xMnum4Gej0HsNXx7erbI6+7" +
            "ks8CxqNiuVk48qTbVKB3BIy8XXLtbVv6x0cBkwuzS3SYrffvuL8MYGzJj3av3BfP4P4p8yeUA8cGxqWBSau4n+Lbu6tYET8AEoMBqIrtuzuPz33D/Uyg28VqUItgxtTrvRGAAoW4LK7BsHTnb5HAE9qB6E+PidGYhCTmYjP24AxuxTGYR98UpLEIdtsXP4zvxXfjO/HN" +
            "+Gp8Kg6p9O8//8ucRns93ZK6vqXfvBccXLuv7b/QH2LjVtyyYLUAdIfJaMmz6Gc83w3yd9OuSLS0WmcxRLj1xYJY/Gz9Wcyfr79XJ6aO3MEKTEAPWsDE988Y4yQM4RGAsEEeIWyiGzLCFsZjgXACY1EQ7oJeOCzclZnHhbthHs4Ld8cQPBPugT6GKdwT841+wr0wzFgl" +
            "3BtpwxHugxHGMeG+mGycEx6EicbdNhusYFqwYCQ4LTJmL2GDnBU20dfcK2xhjXlIOIFl5i3hLhhqjRLuiow1S7gbdlu+cHdMsh4L98DwRD/hntiTmCjcC8nEaeHe2Jq4IdwHmS7ThPtiUxdPeBDWd7nYZgPo33VANqg2Q69YitQ6Owo9Z4daZdd3uGp6etqMDfWqG6rV" +
            "dugF9Fa2qQlL+T9XLQ6aE9V6t1gv22EuqEQborDuROoX0b/87JPUJjeseUFFTUumdfhUHT71Y/hUHT71Y/jULyp6NWWrKLS3ub4d7lBBQeU2LFc6oBRU1fJK5IYVO6KoXVZL/fyyUhRV56ZSBUbUtETSCfyv5h5+6fWveh1+6ZVq2DWVr3vlSDW8qKQ6/fQU+UajkdQl" +
            "2MTHAj+s+sWW/dVSDG6kMpnMjGlzsqHLpne5Khv4Pj9TiyJ2mK9/DBQtR0KcdkQyCIupsue4lZpbS+WbqRnJdCr3USLvFdXOuu3s8CpFtcetlpphTW0P2Lzf5CS7Vd7dli0uWbStXIqQRYAqmgjhoYgSIiisg41Iexzs4H4V93WSS56ONKZhBjbQU6UnpG81bB0dSGwF" +
            "20gTsJTs66zFCFhjImk990XmlnVODgGjI6qxHr0Orfoz7T/P+66rTVqjprMrUJw3ifQX6lM76mRRJ31WJ4s66cczetRXjFbaazPfha9VeeLMKPA9x5zltB2FEqlKz3K9Zy+0NomdaCpDsQcfeSzTdxkxei5SfBVEo9bpglM59Po/v281HJfw+k/PVWe/oqcBW0+bp47H" +
            "HiPt82hLpB+dT89vum/oV7IzhZyETPD7s/7wUzk/elKi3CBl9GsGb38Osox05aR36SmzWs2XPIVFiOQMOSVE8Zu+nG9UnK80krQhiowr65twtdfVXeXR5PsMxqRpc5+7yJOKtDtZ09a356GiPXvgsm6JeaHubzsCOXkfTbmT3VrB5Q1mmbMEi0hl3TNM86VxFF2QMOXL" +
            "XVsuNnEF8mtAHquDQG7DxvVYSOFbbbfVQovG0NwOVV/kFKj3g4eVJZ5/3dALAzGUIl8/MMTDAvEBAC9oe9Hu/WJ/kD9s78f3gfdvpKrPs6H/A+1VkJtADAMnWXFAqEIVqjhw6KEfzQvan/Ud/cQmJdnRjoyzBLUCFAxeZ8aOHSfgk3aU+Kp6oFqX7/mn2s5L6KF+JPoC" +
            "8QOVOqdFAqiOpICIqxbYo82LiTEcccIZF7zjgyiD14JJ9MrUfmsUWAlrx3nybJXJ/b6v1/yIene+vjHV+OQrxNUsQ5XCpSYvtZBCXZeWD85FbA8CGE3TXXlkQwTIJkI1cvQrGmlXFAOiWYslTyXZJ1XX5JWTeT8IXbEKNaBKky2OmVbG17et7AMet8D6Vlh1kZq8sgdj" +
            "RckB7b9Tnx3ELVCmouKUtBHriQZczDu8dlk1i5w+SwGrFYp5mvtc03aE9NdGqP2rSNu3slZt6Ea4zTmuQc/Zcu1q0XEqj52KR/J9YIIwfN4gZOREc/Xdi+O4W6/anb3a9dGGxBoe1NRf8szqSdveBHqRE8SK4PtAbHr2cXFqh9rORFwMcgVACL6qgdi+WY9mzbjJ1agC" +
            "A2Uh8jgnE1wOHu0lvY4L+RGIZCzMHsnS+bwfbljlmyIz/X2Cyaf8t0jUNzue7CR+ZC32wD0kgyL537VkJY/XUtXl4/f3DWJDnotxhmJ67IyMMD2if9qO9ovOCYvrzqWCIf5Z+I4H4iCiODiKdFob37e6M40fO8Cyqtp4cxjXh5iNz2KXn84f5pI65vQ5nKrUWbPJ+fy7" +
            "nMF/ARx6tzdmc0q43dv7n3IcnZlxcHefezcFAJt3de7FX/bLHQWAEAaiuPe/srhiMcEncWDZMkUKwe/oJM+R+Ip1IqfPB1WCakTEbi7q6Jyb9sZ5oAgoYThNGjp24i9uhItx5j6DbyffdZC4XuWaY4gwHjnb1m/m0cxzUENOUK/VVs+f/S1qh79xC6dP/B+YfgEZgCsT" +
            "XRT0T4OPuu6oXXK5r1OxdrBO2xkcikrZz/l3jjozreH9LYpVi1WLVYtVi1VfjbZVGUfbqqNt1dG26mhbFaBB21blBJIgO+HzavBZNXWgKUQCsK8w58oQKQJ19BhR5mHMyUHYuOfWEHOEmL7+B49bDP3Dc/4Q0DW9aSsURmEYfqGx97+Po31eu4MFYdglBEHw47qIMAiD" +
            "YEEQBAuCYF1AGIZBGIRBGARB0MvBA3N8MMccc3wAkN1Zu2umvfeihkba+ueezEstLSxnogE8JMpk9thQDqGsVIWO8JSq0Bmeo/bwEjTUCV57Mn/ragPvUZkO1iWV+8u5rvDRl/NnRyv4CoqawndQ1Bx+0FhL+G0q1xX+oi6QDOAftTWBStAQqh0VUKtBvaTdDVBLAQI/" +
            "ABQAAAAIAEq5UEN0MGyWoggAAHgrAAAFACQAAAAAAAAAIAAAAAAAAABhLnR0ZgoAIAAAAAAAAQAYAABukYC8ys4BJXTvDjUzzwEldO8ONTPPAVBLBQYAAAAAAQABAFcAAADFCAAAAAA=")
        ));

        try {
            zipImage.getNextEntry();
            zipFont.getNextEntry();
            IMAGE = ImageIO.read(zipImage);
            FONT = Font.createFont(0, zipFont);
            FONT = FONT.deriveFont(20.0f);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    int[] speeds = {887,820,753,686,619,552,468,368,284,184,167,150,133,117,100,100,83,83,66,66,50};
    int[] points = {40,100,300,1200};
    int[][][] stones = {
        {{0,1},{1,1},{2,1},{0,2}},      // L 0
        {{0,1},{1,1},{2,1},{3,1}},      // I 1
        {{0,1},{1,1},{2,1},{2,2}},      // J 2
        {{0,1},{1,1},{2,1},{1,2}},      // T 3
        {{0,1},{1,1},{1,2},{2,2}},      // Z 4
        {{1,1},{2,1},{0,2},{1,2}},      // S 5
        {{1,1},{2,1},{1,2},{2,2}}       // O 6
    };
    Color[] colors = {
        new Color(220, 246, 212), // light
        new Color(140, 190, 116), // green
        new Color(60, 98, 92),    // blue
        new Color(4, 30, 20)      // dark
    };
    int FREE = 0;
    int BORDER = 8;
    int DELTA = 20;
    int WIDTH = 10;
    int HEIGHT = 20;
    int SIZE = 24;

    int[][] field = new int[WIDTH][HEIGHT];
    int[][] stone;
    int deltaX;
    int deltaY;
    int curStone;
    int nextStone = new Random().nextInt(7);
    int score;
    int level;
    int lines;
    int steps;
    int speed = speeds[0];

    Timer tickTimer = new Timer();
    Image dbImage;
    Image staticImage;
    Graphics dbg;

    int anim = -1;
    boolean blink = false;
    ArrayList<Integer> removeLinesRows = new ArrayList<Integer>();
    boolean keyDown = true;
    boolean freezeKeys = false;

    private JTetris() {
        setDefaultCloseOperation(2);
        setResizable(false);
        setSize(475, 480);

        addPropertyChangeListener("isDirty", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                repaint();
            }
        });

        reset();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                tickTimer.cancel();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                keyDown = true;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (freezeKeys) {
                    keyDown = false;
                    return;
                }

                switch (e.getKeyCode()) {
                    case 82: reset(); break;
                    case 37: updateField(-1, 0, false); break;
                    case 39: updateField(1, 0, false); break;
                    case 40: if (keyDown) updateField(0, 1, true); break;
                    case 38: if (keyDown) updateField(0, 15, true); break;
                    case 90: rotate(false); break;
                    case 88: rotate(true);
                }
            }
        });

        setVisible(true);
    }

    private void startTickTimer() {
        if (tickTimer != null) {
            tickTimer.cancel();
        }

        freezeKeys = false;

        tickTimer = new Timer();
        tickTimer.schedule(new TimerTask() {
            public void run() {
                updateField(0, 1, false);
            }
        }, 0, speed);
    }

    private void startRemoveLineTimer() {
        if (!removeLinesRows.isEmpty()) {
            anim = 7;
        }

        if (tickTimer != null) {
            tickTimer.cancel();
        }

        tickTimer = new Timer();
        tickTimer.schedule(new TimerTask() {
            public void run() {
                if (anim > 0) {
                    blink = !blink;
                    firePropertyChange("isDirty", false, true);
                }

                if (anim-- < 0) {
                    blink = false;
                    tickTimer.cancel();
                    if (removeLinesRows.size() > 0) {
                        int delta = 0;
                        for (int row : removeLinesRows) {
                            for (int r = row + delta++; r > 2; r--) {
                                for (int c = 0; c < WIDTH; c++) {
                                    field[c][r] = field[c][r - 1];
                                }
                            }
                        }

                        score += points[removeLinesRows.size() - 1] * (level + 1);
                        lines += removeLinesRows.size();
                        level = lines / 10;
                        speed = speeds[level > 20 ? 20 : level];
                        removeLinesRows.clear();
                    }

                    createNextStone();

                    startTickTimer();
                }
            }
        }, 50, 160);
    }

    public static void main(String[] a) {
        new JTetris();
    }

    @Override
    public void paint(Graphics g) {
        if (dbImage == null) {
            dbImage = createImage(getWidth(), getHeight());
            dbg = dbImage.getGraphics();
            dbg.setFont(FONT);

            staticImage = createImage(getWidth(), getHeight());
            Graphics sg = staticImage.getGraphics();
            sg.setFont(FONT);

            sg.setColor(colors[3]);
            sg.fillRect(0, 0, getWidth(), getHeight());

            sg.setColor(colors[0]);
            sg.fillRect(40 + WIDTH * SIZE + SIZE, 70, 170, 58);
            sg.setColor(colors[2]);
            sg.fillRect(40 + WIDTH * SIZE + SIZE, 73, 170, 52);
            sg.setColor(colors[0]);
            sg.fillRect(40 + WIDTH * SIZE + SIZE, 95, 170, 27);
            sg.setColor(colors[2]);
            sg.fillRect(40 + WIDTH * SIZE + SIZE, 98, 170, 3);

            sg.setColor(colors[1]);
            sg.fillRoundRect(40 + 13 * SIZE - 15, 40 + 13 * SIZE - 15, SIZE * 4 + 30, SIZE * 4 + 30, 20, 20);
            sg.setColor(colors[0]);
            sg.fillRoundRect(40 + 13 * SIZE - 12, 40 + 13 * SIZE - 12, SIZE * 4 + 24, SIZE * 4 + 24, 15, 15);

            sg.fillRoundRect(330 - 6, 60 - 6, 120 + 12, 25 + 12, 15, 15);
            sg.fillRoundRect(330 - 6, 155 - 6, 120 + 12, 60 + 12, 15, 15);
            sg.fillRoundRect(330 - 6, 235 - 6, 120 + 12, 60 + 12, 15, 15);
            sg.setColor(colors[2]);
            sg.fillRoundRect(40 + 13 * SIZE - 9, 40 + 13 * SIZE - 9, SIZE * 4 + 18, SIZE * 4 + 18, 15, 15);

            sg.fillRoundRect(330 - 3, 60 - 3, 120 + 6, 25 + 6, 15, 15);
            sg.fillRoundRect(330 - 3, 155 - 3, 120 + 6, 60 + 6, 15, 15);
            sg.fillRoundRect(330 - 3, 235 - 3, 120 + 6, 60 + 6, 15, 15);
            sg.setColor(colors[3]);
            sg.fillRoundRect(40 + 13 * SIZE - 6, 40 + 13 * SIZE - 6, SIZE * 4 + 12, SIZE * 4 + 12, 15, 15);

            sg.setColor(colors[0]);
            sg.fillRoundRect(330, 60, 120, 25, 5, 5);
            sg.fillRect(40 + WIDTH * SIZE + SIZE, 40, 3, (HEIGHT - 2) * SIZE);
            sg.fillRoundRect(40 + 13 * SIZE - 3, 40 + 13 * SIZE - 3, SIZE * 4 + 6, SIZE * 4 + 6, 5, 5);
            sg.fillRoundRect(330, 155, 120, 60, 15, 15);
            sg.fillRoundRect(330, 235, 120, 60, 15, 15);

            sg.setColor(colors[3]);
            sg.drawString("SCORE", 340, 80);
            sg.drawString("LEVEL", 340, 180);
            sg.drawString("LINES", 340, 260);

            for (int r = 0; r < (HEIGHT - 2) * 24 / 18; r++) {
                sg.drawImage(IMAGE, 16, 40 + r * 18, 16 + SIZE, 58 + r * 18, 8 * SIZE, 0, 9 * SIZE, 18, this);
                sg.drawImage(IMAGE, 40 + WIDTH * SIZE, 40 + r * 18, 40 + WIDTH * SIZE + SIZE, 58 + r * 18, 8 * SIZE, 0, 9 * SIZE, 18, this);
            }
        }

        dbg.drawImage(staticImage, 0, 0, this);

        dbg.setColor(colors[3]);
        dbg.drawString(String.format("%6s", score > 999999 ? 999999 : score), 320, 120);
        dbg.drawString(String.format("%4s", level > 20 ? 20 : level), 340, 205);
        dbg.drawString(String.format("%4s", lines > 9999 ? 9999 : lines), 340, 285);

        for (int x = 0; x < 4; x++) {
            drawField(dbg, stones[nextStone][x][0] + 13, stones[nextStone][x][1] + 13, nextStone + 1);
        }

        for (int r = 2; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                drawField(dbg, c, r - 2, field[c][r]);
            }
        }

        if (blink) {
            if (anim == 0)  {
                dbg.setColor(colors[0]);
            } else {
                dbg.setColor(colors[1]);
            }

            for (int row : removeLinesRows) {
                dbg.fillRect(40, 40 + (row - 2) * SIZE, SIZE * WIDTH, SIZE);
            }
        }

        g.drawImage(dbImage, 0, 0, this);
    }

    private void drawField(Graphics g, int x, int y, int idx) {
        if (idx > DELTA) {
            idx -= DELTA;
        }

        g.drawImage(IMAGE, 40 + x * SIZE, 40 + y * SIZE, SIZE + 40 + x * SIZE, SIZE + 40 + y * SIZE, idx * SIZE, 0, (idx + 1) * SIZE, SIZE, this);
    }

    private void createNextStone() {
        curStone = nextStone;
        stone = stones[curStone];
        deltaX = (WIDTH + 1) / 2 - 2;
        deltaY = 1;

        if (!isMoveable(0, 0)) {
            tickTimer.cancel();
            JOptionPane.showMessageDialog(this, "Game over! Press OK for a new game.");
            reset();
            return;
        }

        updateField(0, 0, false);
        nextStone = new Random().nextInt(7);
    }

    private void reset() {
        score = 0;
        steps = 0;
        lines = 0;
        level = 0;
        speed = speeds[0];

        for (int r = 0; r < HEIGHT; r++) {
            for (int c = 0; c < WIDTH; c++) {
                field[c][r] = FREE;
            }
        }

        createNextStone();
        startTickTimer();
    }

    private void updateField(int hor, int ver, boolean player) {
        if (isMoveable(hor, ver)) {
            if (player) {
                steps++;
            }

            for (int x = 0; x < 4; x++) {
                field[stone[x][0] + deltaX][stone[x][1] + deltaY] = FREE;
            }

            deltaX += hor;
            deltaY += ver;
            

            for (int x = 0; x < 4; x++) {
                field[stone[x][0] + deltaX][stone[x][1] + deltaY] = curStone + 1;
            }

            firePropertyChange("isDirty", false, true);
        } else if (ver == 1) {
            freezeKeys = true;
            fixStone();
            removeLines();
        }
    }

    private void removeLines() {
        outer:
        for (int r = HEIGHT - 1; r >= 0; r--) {
            for (int c = 0; c < WIDTH; c++) {
                if (field[c][r] == FREE) {
                    continue outer;
                }
            }

            removeLinesRows.add(r);
        }

        startRemoveLineTimer();
    }

    private void fixStone() {
        score += steps;
        steps = 0;

        for (int x = 0; x < 4; x++) {
            field[stone[x][0] + deltaX][stone[x][1] + deltaY] = curStone + 1 + DELTA;
        }
    }

    private boolean isMoveable(int hor, int ver) {
        for (int x = 0; x < 4; x++) {
            int c = stone[x][0] + deltaX + hor;
            int r = stone[x][1] + deltaY + ver;

            if (c < 0 || c >= WIDTH || r < 0 || r >= HEIGHT) {
                return false;
            }

            if (field[c][r] > DELTA) {
                return false;
            }
        }

        return true;
    }

    private void rotate(boolean r) {
        if (curStone == 6) { // O
            return;
        }

        synchronized (field) {
            int[][] o = new int[4][2];

            for (int x = 0; x < 4; x++) {
                o[x] = new int[] { r ? 3 - stone[x][1] : stone[x][1], r ? stone[x][0] : 3 - stone[x][0] };
            }

            if (canRotate(o)) {
                for (int x = 0; x < 4; x++) {
                    field[stone[x][0] + deltaX][stone[x][1] + deltaY] = FREE;
                }

                stone = o;

                for (int x = 0; x < 4; x++) {
                    field[stone[x][0] + deltaX][stone[x][1] + deltaY] = curStone + 1;
                }

                firePropertyChange("isDirty", false, true);
            }
        }
    }

    private boolean canRotate(int[][] o) {
        for (int x = 0; x < 4; x++) {
            if (o[x][0] + deltaX < 0 ||
                o[x][0] + deltaX >= WIDTH ||
                o[x][1] + deltaY >= HEIGHT ||
                field[o[x][0] + deltaX][o[x][1] + deltaY] > DELTA) {
                return false;
            }
        }

        return true;
    }
}
