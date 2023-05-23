package org.tinkoff.task10;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Object[] incomingParameters = getAndCheckIncomingParameters();
        Pair[] vertexCoordinates = (Pair[]) incomingParameters[1];
        int minX = getMinX(vertexCoordinates);
        int maxX = getMaxX(vertexCoordinates);
        float xForFinding = ((float) minX + maxX) / 2;
        float left = minX;
        float right = maxX;
        while (true) {
            List<List<Pair>> coordinatesOfVerticesToCalculateSpaces =
                    getCoordinatesOfVerticesToCalculateSpaces(vertexCoordinates, xForFinding);
            float theseAreEqualAreas = theseAreEqualAreas(coordinatesOfVerticesToCalculateSpaces);
            if (theseAreEqualAreas < 0) {
                left = xForFinding;
                xForFinding = (left + right) / 2;
            } else if (theseAreEqualAreas == 0) {
                System.out.println(new DecimalFormat(".000000000").format(xForFinding));
                break;
            } else {
                right = xForFinding;
                xForFinding = (left + right) / 2;
            }
        }
    }

    private static float theseAreEqualAreas(List<List<Pair>> areas) {
        float area1 = 0;
        float area2 = 0;
        for (int i = 0; i < areas.get(0).size(); i++) {
            if (i == areas.get(0).size() - 1) {
                area1 += areas.get(0).get(i).getX() * areas.get(0).get(0).getY();
                area1 -= areas.get(0).get(i).getY() * areas.get(0).get(0).getX();
            } else {
                area1 += areas.get(0).get(i).getX() * areas.get(0).get(i + 1).getY();
                area1 -= areas.get(0).get(i).getY() * areas.get(0).get(i + 1).getX();
            }
        }
        for (int i = 0; i < areas.get(1).size(); i++) {
            if (i == areas.get(1).size() - 1) {
                area2 += areas.get(1).get(i).getX() * areas.get(1).get(0).getY();
                area2 -= areas.get(1).get(i).getY() * areas.get(1).get(0).getX();
            } else {
                area2 += areas.get(1).get(i).getX() * areas.get(1).get(i + 1).getY();
                area2 -= areas.get(1).get(i).getY() * areas.get(1).get(i + 1).getX();
            }
        }
        return Math.abs(area1) - Math.abs(area2);
    }

    private static List<List<Pair>> getCoordinatesOfVerticesToCalculateSpaces(Pair[] vertexCoordinates, float xForFinding) {
        List<Pair> vertexCoordinates1 = new ArrayList<>();
        List<Pair> vertexCoordinates2 = new ArrayList<>();
        List<List<Pair>> result = List.of(vertexCoordinates1, vertexCoordinates2);
        float x1, x2, y1, y2;
        for (int i = 1; i <= vertexCoordinates.length; i++) {
            if (i == vertexCoordinates.length) {
                x1 = vertexCoordinates[0].getX();
                y1 = vertexCoordinates[0].getY();
                x2 = vertexCoordinates[i - 1].getX();
                y2 = vertexCoordinates[i - 1].getY();

            } else {
                x1 = vertexCoordinates[i - 1].getX();
                y1 = vertexCoordinates[i - 1].getY();
                x2 = vertexCoordinates[i].getX();
                y2 = vertexCoordinates[i].getY();
            }
            float minX = Math.min(x1, x2);
            float maxX = Math.max(x1, x2);
            Pair minPairForX = null;
            Pair maxPairForX = null;
            if (i == vertexCoordinates.length) {
                minPairForX = (vertexCoordinates[0].getX() < vertexCoordinates[i - 1].getX())
                        ? vertexCoordinates[0] : vertexCoordinates[i - 1];
                maxPairForX = (vertexCoordinates[0].getX() > vertexCoordinates[i - 1].getX())
                        ? vertexCoordinates[0] : vertexCoordinates[i - 1];
            } else {
                minPairForX = (vertexCoordinates[i].getX() < vertexCoordinates[i - 1].getX())
                        ? vertexCoordinates[i] : vertexCoordinates[i - 1];
                maxPairForX = (vertexCoordinates[i].getX() > vertexCoordinates[i - 1].getX())
                        ? vertexCoordinates[i] : vertexCoordinates[i - 1];
            }
            if (xForFinding < minX) {
                if (i == vertexCoordinates.length) {
                    addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i - 1]);
                    addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[0]);
                } else {
                    addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i - 1]);
                    addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i]);
                }
            } else if (xForFinding == minX) {
                addToVertexCoordinates(vertexCoordinates1, minPairForX);
                addToVertexCoordinates(vertexCoordinates2, maxPairForX);
                addToVertexCoordinates(vertexCoordinates2, minPairForX);
            } else if (minX < xForFinding && xForFinding < maxX) {
                float y = (xForFinding - x1) * (y2 - y1) / (x2 - x1) + y1;
                if (i == vertexCoordinates.length) {
                    if (vertexCoordinates[0].getX() < vertexCoordinates[i - 1].getX()) {
                        addToVertexCoordinates(vertexCoordinates1, new Pair(xForFinding, y));
                        addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[0]);
                        addToVertexCoordinates(vertexCoordinates2, new Pair(xForFinding, y));
                        addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i - 1]);
                    } else {
                        addToVertexCoordinates(vertexCoordinates1, new Pair(xForFinding, y));
                        addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[i - 1]);
                        addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i - 1]);
                        addToVertexCoordinates(vertexCoordinates2, new Pair(xForFinding, y));
                    }
                } else {
                    if (vertexCoordinates[i].getX() < vertexCoordinates[i - 1].getX()) {
                        addToVertexCoordinates(vertexCoordinates1, new Pair(xForFinding, y));
                        addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[i]);
                        addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i - 1]);
                        addToVertexCoordinates(vertexCoordinates2, new Pair(xForFinding, y));
                    } else {
                        addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[i - 1]);
                        addToVertexCoordinates(vertexCoordinates1, new Pair(xForFinding, y));
                        addToVertexCoordinates(vertexCoordinates2, new Pair(xForFinding, y));
                        addToVertexCoordinates(vertexCoordinates2, vertexCoordinates[i]);
                    }
                }
            } else if (xForFinding == maxX) {
                addToVertexCoordinates(vertexCoordinates1, maxPairForX);
                addToVertexCoordinates(vertexCoordinates1, minPairForX);
                addToVertexCoordinates(vertexCoordinates2, maxPairForX);
            } else if (maxX < xForFinding) {
                if (i == vertexCoordinates.length) {
                    addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[i - 1]);
                    addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[0]);
                } else {
                    addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[i - 1]);
                    addToVertexCoordinates(vertexCoordinates1, vertexCoordinates[i]);
                }
            }
        }
        return result;
    }

    private static void addToVertexCoordinates(List<Pair> vertexCoordinates, Pair vertexCoordinate) {
        if (needToAdd(vertexCoordinates, vertexCoordinate)) {
            vertexCoordinates.add(vertexCoordinate);
        }
    }

    private static boolean needToAdd(List<Pair> vertexCoordinates, Pair vertexCoordinate) {
        if (vertexCoordinates.size() == 0) {
            return true;
        }
        for (Pair pair : vertexCoordinates) {
            if (pair.getX() == vertexCoordinate.getX() && pair.getY() == vertexCoordinate.getY()) {
                return false;
            }
        }
        return true;
    }

    private static int getMinX(Pair[] vertexCoordinates) {
        int minX = Integer.MAX_VALUE;
        for (Pair vertexCoordinate : vertexCoordinates) {
            if (vertexCoordinate.getX() < minX) {
                minX = (int) vertexCoordinate.getX();
            }
        }
        return minX;
    }

    private static int getMaxX(Pair[] vertexCoordinates) {
        int maxX = Integer.MIN_VALUE;
        for (Pair vertexCoordinate : vertexCoordinates) {
            if (vertexCoordinate.getX() > maxX) {
                maxX = (int) vertexCoordinate.getX();
            }
        }
        return maxX;
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[2];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckNumberOfPolygonVertices(scanner);
        result[1] = getAndVertexCoordinates(scanner, (int) result[0]);
        return result;
    }

    private static int getAndCheckNumberOfPolygonVertices(final Scanner scanner) {
        String nextLine = scanner.nextLine();
        int numberOfPolygonVertices = Integer.parseInt(nextLine);
        if (numberOfPolygonVertices < 1 || numberOfPolygonVertices > 1000) {
            throw new NumberFormatException();
        }
        return numberOfPolygonVertices;
    }

    private static Pair[] getAndVertexCoordinates(final Scanner scanner, final int numberOfPolygonVertices) {
        Pair[] vertexCoordinates = new Pair[numberOfPolygonVertices];
        int counter = 0;
        String nextLine = "";
        while (true) {
            nextLine = scanner.nextLine();
            if ("".equals(nextLine)) {
                continue;
            }
            String[] strings = nextLine.split(" ");
            int x = 0;
            int y = 0;
            int counterXY = 0;
            for (int i = 0; i < strings.length; i++) {
                if (strings[i] != "") {
                    if (counterXY == 0) {
                        counterXY++;
                        x = Integer.parseInt(strings[i]);

                    } else if (counterXY == 1) {
                        y = Integer.parseInt(strings[i]);
                    }
                }
            }
            vertexCoordinates[counter] = new Pair(x, y);
            counter++;
            if (counter == numberOfPolygonVertices) {
                break;
            }
        }
        for (Pair vertexCoordinate : vertexCoordinates) {
            if (vertexCoordinate.getX() < -1000 || vertexCoordinate.getX() > 1000
                    || vertexCoordinate.getY() < -1000 || vertexCoordinate.getY() > 1000) {
                throw new NumberFormatException();
            }
        }
        return vertexCoordinates;
    }

    private static class Pair {
        private final float x;
        private final float y;

        public Pair(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}