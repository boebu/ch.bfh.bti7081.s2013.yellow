package ch.bfh.bti7081.s2013.yellow.dao.generic;

import ch.bfh.bti7081.s2013.yellow.model.generic.YellowEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Janosch Rohdewald
 * Implementation of the YellowEntityDao
 */
@Repository
public class YellowEntityDAOImpl extends GenericDAOImpl<YellowEntity> implements YellowEntityDAO {

    public YellowEntityDAOImpl() {
        super(YellowEntity.class);
    }

}
