import java.util.Scanner;                                                     // Mengambil Scanner untuk input dari terminal

public class Main {                                                           // Class utama, file harus bernama Main.java

    static class Node {                                                       // Node sebagai elemen pada singly linked list
        int data;                                                             // Menyimpan nilai pada node
        Node next;                                                            // Menyimpan referensi ke node berikutnya
        Node(int data) {                                                      // Konstruktor untuk membuat node baru
            this.data = data;                                                 // Mengisi nilai data node
        }                                                                     // Akhir konstruktor Node
    }                                                                         // Akhir class Node

    static class SinglyLinkedList {                                           // Struktur singly linked list (tanpa memakai nama head/tail)
        private final Node END;                                               // Node sentinel sebagai penanda batas akhir list
        private Node first;                                                   // Penunjuk elemen pertama pada list
        private int size;                                                     // Jumlah elemen yang tersimpan

        SinglyLinkedList() {                                                  // Konstruktor list
            END = new Node(Integer.MIN_VALUE);                                // Membuat node sentinel
            END.next = END;                                                   // Sentinel menunjuk dirinya sendiri sebagai batas traversal
            first = END;                                                      // Kondisi awal: list kosong
            size = 0;                                                         // Ukuran awal nol
        }                                                                     // Akhir konstruktor list

        boolean contains(int value) {                                         // Mengecek apakah value sudah ada (mencegah duplikat)
            Node cur = first;                                                 // Mulai dari elemen pertama
            while (cur != END) {                                              // Berhenti ketika mencapai sentinel
                if (cur.data == value) return true;                           // Jika sama, berarti duplikat
                cur = cur.next;                                               // Geser ke node berikutnya
            }                                                                 // Akhir traversal
            return false;                                                     // Tidak ditemukan
        }                                                                     // Akhir contains

        boolean insertAtHeadUnique(int value) {                               // Menyisipkan elemen di awal list (insert at the head)
            if (contains(value)) return false;                                // Menolak jika nilai sudah ada
            Node n = new Node(value);                                         // Membuat node baru berisi value
            n.next = first;                                                   // Node baru menunjuk elemen pertama lama
            first = n;                                                        // Elemen pertama diperbarui menjadi node baru
            size++;                                                           // Ukuran list bertambah
            return true;                                                      // Menandakan insert berhasil
        }                                                                     // Akhir insertAtHeadUnique

        void print() {                                                        // Menampilkan isi list tanpa menampilkan penanda akhir
            System.out.print("List (size=" + size + "): ");                   // Mencetak ukuran list
            if (size == 0) {                                                  // Jika belum ada elemen
                System.out.println("(kosong)");                               // Menampilkan kondisi kosong
                return;                                                       // Mengakhiri fungsi print
            }                                                                 // Akhir kondisi kosong

            Node cur = first;                                                 // Mulai cetak dari elemen pertama
            while (cur != END) {                                              // Cetak sampai sentinel
                System.out.print(cur.data);                                   // Cetak data node
                if (cur.next != END) System.out.print(" -> ");                // Cetak panah hanya antar elemen
                cur = cur.next;                                               // Pindah ke node berikutnya
            }                                                                 // Akhir loop cetak
            System.out.println();                                             // Pindah baris setelah selesai
        }                                                                     // Akhir print
    }                                                                         // Akhir class SinglyLinkedList

    private static int readInt(Scanner sc, String prompt) {                   // Membaca input angka dengan validasi sederhana
        System.out.print(prompt);                                             // Menampilkan prompt
        while (!sc.hasNextInt()) {                                            // Memastikan input adalah angka
            System.out.print("Input harus angka. Coba lagi: ");               // Meminta input ulang
            sc.next();                                                        // Membuang input yang salah
        }                                                                     // Akhir validasi
        return sc.nextInt();                                                  // Mengembalikan angka yang valid
    }                                                                         // Akhir readInt

    public static void main(String[] args) {                                  // Titik awal program
        Scanner sc = new Scanner(System.in);                                  // Membuat Scanner untuk input terminal
        SinglyLinkedList list = new SinglyLinkedList();                       // Membuat objek singly linked list

        while (true) {                                                        // Loop menu sampai pengguna keluar
            System.out.println("\nMenu: 1=InsertAwal  2=Tampilkan  0=Keluar"); // Menampilkan menu program
            int pilih = readInt(sc, "Pilih menu: ");                          // Membaca pilihan menu

            if (pilih == 0) {                                                 // Jika pengguna memilih keluar
                System.out.println("Program selesai.");                       // Menampilkan pesan penutup
                break;                                                        // Menghentikan loop
            }                                                                 // Akhir kondisi keluar

            if (pilih == 1) {                                                 // Jika memilih insert di awal
                int value = readInt(sc, "Masukkan nilai yang ditambah: ");    // Membaca nilai yang akan dimasukkan
                boolean ok = list.insertAtHeadUnique(value);                  // Menyisipkan nilai di awal tanpa duplikat
                if (!ok) System.out.println("Nilai sudah ada, duplikat tidak diizinkan."); // Pesan jika duplikat
                list.print();                                                 // Menampilkan list setelah operasi
            } else if (pilih == 2) {                                          // Jika memilih tampilkan list
                list.print();                                                 // Menampilkan isi list
            } else {                                                          // Jika pilihan menu tidak sesuai
                System.out.println("Pilihan tidak valid.");                   // Menampilkan peringatan
            }                                                                 // Akhir percabangan menu
        }                                                                     // Akhir loop menu

        sc.close();                                                           // Menutup Scanner
    }                                                                         // Akhir main
}                                                                             // Akhir program
