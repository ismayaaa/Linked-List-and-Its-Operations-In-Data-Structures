import java.util.Scanner;                                                     // Scanner untuk membaca input dari terminal

public class Main {                                                           // Class utama, file wajib bernama Main.java

    static class Node {                                                       // Node sebagai elemen singly linked list
        int data;                                                             // Nilai yang disimpan di node
        Node next;                                                            // Referensi ke node berikutnya
        Node(int data) {                                                      // Konstruktor node
            this.data = data;                                                 // Mengisi nilai data
        }                                                                     // Akhir konstruktor Node
    }                                                                         // Akhir class Node

    static class SinglyLinkedList {                                           // Singly linked list (tanpa memakai nama head/tail)
        private final Node END;                                               // Node sentinel sebagai penanda batas akhir
        private Node first;                                                   // Penunjuk elemen pertama
        private int size;                                                     // Jumlah elemen yang tersimpan

        SinglyLinkedList() {                                                  // Konstruktor list
            END = new Node(Integer.MIN_VALUE);                                // Membuat sentinel
            END.next = END;                                                   // Sentinel menunjuk dirinya sendiri
            first = END;                                                      // Kondisi awal list kosong
            size = 0;                                                         // Ukuran awal 0
        }                                                                     // Akhir konstruktor

        boolean insertAtTailUnique(int value) {                               // Menyisipkan elemen di akhir list (tail)
            Node n = new Node(value);                                         // Membuat node baru
            n.next = END;                                                     // Node baru selalu menunjuk sentinel sebagai batas

            if (size == 0) {                                                  // Jika list masih kosong
                first = n;                                                    // Elemen pertama menjadi node baru
                size++;                                                       // Ukuran bertambah
                return true;                                                  // Insert berhasil
            }                                                                 // Akhir kondisi list kosong

            Node cur = first;                                                 // Mulai dari elemen pertama
            while (cur.next != END) {                                         // Traversal sampai node terakhir (sebelum sentinel)
                if (cur.data == value) return false;                          // Cek duplikat selama traversal
                cur = cur.next;                                               // Pindah ke node berikutnya
            }                                                                 // Akhir traversal menuju node terakhir
            if (cur.data == value) return false;                              // Cek duplikat pada node terakhir

            cur.next = n;                                                     // Menyambungkan node terakhir ke node baru
            size++;                                                           // Ukuran bertambah
            return true;                                                      // Insert berhasil
        }                                                                     // Akhir insertAtTailUnique

        void print() {                                                        // Menampilkan isi list tanpa penanda akhir
            System.out.print("List (size=" + size + "): ");                   // Header tampilan
            if (size == 0) {                                                  // Jika list kosong
                System.out.println("(kosong)");                               // Tampilkan kosong
                return;                                                       // Selesai
            }                                                                 // Akhir kondisi kosong

            Node cur = first;                                                 // Mulai cetak dari elemen pertama
            while (cur != END) {                                              // Cetak sampai sentinel
                System.out.print(cur.data);                                   // Cetak nilai node
                if (cur.next != END) System.out.print(" -> ");                // Panah hanya antar elemen
                cur = cur.next;                                               // Pindah ke node berikutnya
            }                                                                 // Akhir loop cetak
            System.out.println();                                             // Pindah baris
        }                                                                     // Akhir print
    }                                                                         // Akhir class SinglyLinkedList

    private static int readInt(Scanner sc, String prompt) {                   // Membaca angka dengan validasi sederhana
        System.out.print(prompt);                                             // Menampilkan prompt
        while (!sc.hasNextInt()) {                                            // Jika input bukan angka
            System.out.print("Input harus angka. Coba lagi: ");               // Meminta input ulang
            sc.next();                                                        // Membuang input yang salah
        }                                                                     // Akhir validasi
        return sc.nextInt();                                                  // Mengembalikan angka yang valid
    }                                                                         // Akhir readInt

    public static void main(String[] args) {                                  // Titik awal program
        Scanner sc = new Scanner(System.in);                                  // Membuat scanner
        SinglyLinkedList list = new SinglyLinkedList();                       // Membuat objek list

        while (true) {                                                        // Loop menu sampai pengguna keluar
            System.out.println("\nMenu: 1=InsertAkhir  2=Tampilkan  0=Keluar"); // Menu program
            int pilih = readInt(sc, "Pilih menu: ");                          // Membaca pilihan menu

            if (pilih == 0) {                                                 // Jika pengguna memilih keluar
                System.out.println("Program selesai.");                       // Pesan penutup
                break;                                                        // Keluar dari loop
            }                                                                 // Akhir kondisi keluar

            if (pilih == 1) {                                                 // Opsi insert di akhir
                int value = readInt(sc, "Masukkan nilai yang ditambah: ");    // Membaca nilai yang akan dimasukkan
                boolean ok = list.insertAtTailUnique(value);                  // Menambah di akhir tanpa duplikat
                if (!ok) System.out.println("Nilai sudah ada, duplikat tidak diizinkan."); // Pesan jika duplikat
                list.print();                                                 // Menampilkan list setelah operasi
            } else if (pilih == 2) {                                          // Opsi tampilkan list
                list.print();                                                 // Menampilkan isi list
            } else {                                                          // Jika pilihan tidak sesuai
                System.out.println("Pilihan tidak valid.");                   // Peringatan input menu salah
            }                                                                 // Akhir percabangan menu
        }                                                                     // Akhir loop menu

        sc.close();                                                           // Menutup scanner
    }                                                                         // Akhir main
}                                                                             // Akhir program
