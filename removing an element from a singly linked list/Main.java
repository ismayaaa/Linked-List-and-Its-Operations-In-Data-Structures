import java.util.Scanner;                                                     // Mengambil Scanner untuk input dari terminal

public class Main {                                                           // Nama class publik, file harus Main.java

    static class Node {                                                       // Representasi satu node pada singly linked list
        int data;                                                             // Menyimpan nilai pada node
        Node next;                                                            // Menyimpan referensi ke node berikutnya
        Node(int data) {                                                      // Konstruktor node
            this.data = data;                                                 // Mengisi nilai data
        }                                                                     // Akhir konstruktor
    }                                                                         // Akhir class Node

    static class SinglyLinkedList {                                           // Struktur singly linked list (tanpa variabel head dan tail)
        private final Node END;                                               // Node penanda akhir agar traversal punya batas jelas
        private Node first;                                                   // Penunjuk elemen pertama (saya tidak memakai nama head)
        private int size;                                                     // Menyimpan jumlah elemen pada list

        SinglyLinkedList() {                                                  // Konstruktor list
            END = new Node(Integer.MIN_VALUE);                                // Membuat node sentinel sebagai penanda akhir
            END.next = END;                                                   // Sentinel menunjuk dirinya sendiri agar stabil
            first = END;                                                      // Kondisi awal: list kosong
            size = 0;                                                         // Ukuran awal 0
        }                                                                     // Akhir konstruktor

        boolean contains(int value) {                                         // Mengecek apakah value sudah ada (untuk cegah duplikat)
            Node cur = first;                                                 // Mulai dari elemen pertama
            while (cur != END) {                                              // Berhenti saat mencapai sentinel
                if (cur.data == value) return true;                           // Jika ketemu, berarti sudah ada
                cur = cur.next;                                               // Geser ke node berikutnya
            }                                                                 // Akhir traversal
            return false;                                                     // Tidak ditemukan
        }                                                                     // Akhir contains

        boolean addUnique(int value) {                                        // Menambah elemen (dibuat untuk uji coba remove)
            if (contains(value)) return false;                                // Menolak jika value sudah ada
            Node n = new Node(value);                                         // Membuat node baru
            n.next = END;                                                     // Node baru selalu mengarah ke sentinel sebagai akhir
            if (size == 0) {                                                  // Jika list kosong
                first = n;                                                    // Elemen pertama menjadi node baru
            } else {                                                          // Jika list sudah berisi
                Node cur = first;                                             // Mulai dari elemen pertama
                while (cur.next != END) {                                     // Traversal sampai node terakhir
                    cur = cur.next;                                           // Geser sampai sebelum sentinel
                }                                                             // Akhir traversal
                cur.next = n;                                                 // Sambungkan node terakhir ke node baru
            }                                                                 // Akhir kondisi
            size++;                                                           // Ukuran bertambah
            return true;                                                      // Penambahan berhasil
        }                                                                     // Akhir addUnique

        boolean removeFirstOccurrence(int target) {                            // Menghapus kemunculan pertama target
            if (size == 0) return false;                                      // Jika kosong, tidak ada yang bisa dihapus

            if (first.data == target) {                                       // Jika target berada di elemen pertama
                first = first.next;                                           // Melepas elemen pertama dengan menggeser first
                size--;                                                       // Ukuran berkurang
                if (size == 0) first = END;                                   // Jika habis, kembalikan ke kondisi kosong
                return true;                                                  // Penghapusan berhasil
            }                                                                 // Akhir kasus target di elemen pertama

            Node prev = first;                                                // Menyimpan node sebelum node yang diperiksa
            Node cur  = first.next;                                           // Mulai cek dari node kedua
            while (cur != END) {                                              // Traversal sampai sentinel
                if (cur.data == target) {                                     // Jika ketemu target
                    prev.next = cur.next;                                     // Lewati cur agar cur terhapus dari rantai
                    size--;                                                   // Ukuran berkurang
                    return true;                                              // Penghapusan berhasil
                }                                                             // Akhir kondisi ketemu
                prev = cur;                                                   // Geser prev mengikuti cur
                cur  = cur.next;                                              // Geser cur ke node berikutnya
            }                                                                 // Akhir traversal

            return false;                                                     // Target tidak ditemukan
        }                                                                     // Akhir removeFirstOccurrence

        void print() {                                                        // Menampilkan isi list tanpa penanda akhir
            System.out.print("List (size=" + size + "): ");                   // Header tampilan
            if (size == 0) {                                                  // Jika kosong
                System.out.println("(kosong)");                               // Tampilkan kosong
                return;                                                       // Selesai
            }                                                                 // Akhir kondisi kosong

            Node cur = first;                                                 // Mulai dari elemen pertama
            while (cur != END) {                                              // Cetak sampai sentinel
                System.out.print(cur.data);                                   // Cetak nilai node
                if (cur.next != END) System.out.print(" -> ");                // Panah hanya antar elemen
                cur = cur.next;                                               // Geser ke node berikutnya
            }                                                                 // Akhir loop cetak
            System.out.println();                                             // Pindah baris
        }                                                                     // Akhir print
    }                                                                         // Akhir class SinglyLinkedList

    private static int readInt(Scanner sc, String prompt) {                   // Membaca input angka dengan aman
        System.out.print(prompt);                                             // Menampilkan prompt
        while (!sc.hasNextInt()) {                                            // Validasi jika bukan angka
            System.out.print("Input harus angka. Coba lagi: ");               // Meminta input ulang
            sc.next();                                                        // Membuang input yang salah
        }                                                                     // Akhir validasi
        return sc.nextInt();                                                  // Mengambil angka yang valid
    }                                                                         // Akhir readInt

    public static void main(String[] args) {                                  // Titik awal program
        Scanner sc = new Scanner(System.in);                                  // Scanner untuk membaca terminal
        SinglyLinkedList list = new SinglyLinkedList();                       // Membuat singly linked list

        while (true) {                                                        // Loop menu sampai pengguna keluar
            System.out.println("\nMenu: 1=Tambah  2=Remove  3=Tampilkan  0=Keluar"); // Menampilkan menu
            int pilih = readInt(sc, "Pilih menu: ");                          // Membaca pilihan menu

            if (pilih == 0) {                                                 // Kondisi keluar
                System.out.println("Program selesai.");                       // Pesan penutup
                break;                                                        // Keluar dari loop
            }                                                                 // Akhir keluar

            if (pilih == 1) {                                                 // Menu tambah
                int value = readInt(sc, "Masukkan nilai yang ditambah: ");    // Input nilai baru
                boolean ok = list.addUnique(value);                           // Tambah tanpa duplikat
                if (!ok) System.out.println("Nilai sudah ada, duplikat tidak diizinkan."); // Pesan jika duplikat
                list.print();                                                 // Tampilkan kondisi list
            } else if (pilih == 2) {                                          // Menu remove
                int target = readInt(sc, "Masukkan nilai yang dihapus: ");    // Input target yang dihapus
                boolean ok = list.removeFirstOccurrence(target);              // Menghapus kemunculan pertama
                System.out.println("Hasil remove: " + ok);                    // Menampilkan status berhasil/gagal
                list.print();                                                 // Tampilkan kondisi list
            } else if (pilih == 3) {                                          // Menu tampilkan
                list.print();                                                 // Cetak list
            } else {                                                          // Input menu tidak valid
                System.out.println("Pilihan tidak valid.");                   // Pesan kesalahan
            }                                                                 // Akhir percabangan
        }                                                                     // Akhir loop

        sc.close();                                                           // Menutup scanner
    }                                                                         // Akhir main
}                                                                             // Akhir program
