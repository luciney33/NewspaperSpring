package newspaperoot.dao;
import newspaperoot.dao.model.CredentialEntity;
import newspaperoot.dao.model.ReaderEntity;
import java.util.List;

public interface ReaderRepository {
    List<ReaderEntity> getAll();
    ReaderEntity getReaderById(int id);
    int save(ReaderEntity reader, CredentialEntity credential, boolean confirmation);
    int delete(ReaderEntity reader, boolean confirmation);

}
