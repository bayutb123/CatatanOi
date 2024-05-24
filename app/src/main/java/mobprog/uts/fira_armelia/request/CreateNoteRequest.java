package mobprog.uts.fira_armelia.request;

public class CreateNoteRequest {
    private String req;
    private String nim;
    private String judul_catatan;
    private String detail_catatan;

    public CreateNoteRequest(String req, String nim, String judul_catatan, String detail_catatan) {
        this.req = req;
        this.nim = nim;
        this.judul_catatan = judul_catatan;
        this.detail_catatan = detail_catatan;
    }
}
