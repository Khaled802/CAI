package org.example;

public class OperatingSystem {
    public static OS getOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            return OS.LINUX;
        } else {
            return OS.OTHER;
        }
    }

    public enum OS {
        WINDOWS("WINDOWS"), LINUX("LINUX"), OTHER("OTHER");
        private final String os;
        OS(String os) {
            this.os = os;
        }

        @Override
        public String toString() {
            return os;
        }
    }
}

